package mimly.emergencyroom.repository.dao;

import mimly.emergencyroom.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MainRepository implements mimly.emergencyroom.repository.dao.Repository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public MainRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Professional getProfessional(String username) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM Professional WHERE username = ?",
                RepositoryHelper::mapToProfessional,
                username
        );
    }

    @Override
    public List<Priority> getPriorities() {
        return this.jdbcOperations.query(
                "SELECT * FROM Priority",
                RepositoryHelper::mapToPriority
        );
    }

    @Override
    public Priority getPriorityByName(String name) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM Priority WHERE name = ?",
                RepositoryHelper::mapToPriority,
                name
        );
    }

    @Override
    public Priority getPriorityByID(int ID) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM Priority WHERE \"ID\" = ?",
                RepositoryHelper::mapToPriority,
                ID
        );
    }

    @Override
    public List<MedicalIssue> getMedicalIssues() {
        return this.jdbcOperations.query(
                "SELECT * FROM MedicalIssue ORDER BY name",
                RepositoryHelper::mapToMedicalIssue
        );
    }

    @Override
    public MedicalIssue getMedicalIssueByName(String name) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM MedicalIssue WHERE name = ?",
                RepositoryHelper::mapToMedicalIssue,
                name
        );
    }

    @Override
    public MedicalIssue getMedicalIssueByID(int ID) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM MedicalIssue WHERE \"ID\" = ?",
                RepositoryHelper::mapToMedicalIssue,
                ID
        );
    }

    @Override
    public List<Drug> getDrugs() {
        return this.jdbcOperations.query(
                "SELECT * FROM Drug ORDER BY name",
                RepositoryHelper::mapToDrug
        );
    }

    @Override
    public Drug getDrugByName(String name) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM Drug WHERE name = ?",
                RepositoryHelper::mapToDrug,
                name
        );
    }

    @Override
    public Drug getDrugByID(int ID) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM Drug WHERE \"ID\" = ?",
                RepositoryHelper::mapToDrug,
                ID
        );
    }

    @Override
    public List<MedicalProcedure> getMedicalProcedures() {
        return this.jdbcOperations.query(
                "SELECT * FROM MedicalProcedure ORDER BY name",
                RepositoryHelper::mapToMedicalProcedure
        );
    }

    @Override
    public MedicalProcedure getMedicalProcedureByName(String name) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM MedicalProcedure WHERE name = ?",
                RepositoryHelper::mapToMedicalProcedure,
                name
        );
    }

    @Override
    public MedicalProcedure getMedicalProcedureByID(int ID) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM MedicalProcedure WHERE \"ID\" = ?",
                RepositoryHelper::mapToMedicalProcedure,
                ID
        );
    }

    @Override
    public List<MedicalProcedure> getMedicalProceduresFor(int medicalIssueID) {
        return this.jdbcOperations.query(
                "SELECT * FROM MedicalProcedure WHERE \"medicalIssueID\" = ? ORDER BY name",
                RepositoryHelper::mapToMedicalProcedure,
                medicalIssueID
        );
    }

    @Override
    public List<Outcome> getOutcomes() {
        return this.jdbcOperations.query(
                "SELECT * FROM Outcome",
                RepositoryHelper::mapToOutcome
        );
    }

    @Override
    public List<EmergencyTeam> getEmergencyTeams() {
        return this.jdbcOperations.query(
                "SELECT * FROM EmergencyTeam",
                RepositoryHelper::mapToEmergencyTeam
        );
    }

    @Override
    public EmergencyTeam getEmergencyTeamByName(String name) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM EmergencyTeam WHERE name = ?",
                RepositoryHelper::mapToEmergencyTeam,
                name
        );
    }

    @Override
    public EmergencyTeam getEmergencyTeamByID(int ID) {
        return this.jdbcOperations.queryForObject(
                "SELECT * FROM EmergencyTeam WHERE \"ID\" = ?",
                RepositoryHelper::mapToEmergencyTeam,
                ID
        );
    }

    @Override
    public List<Patient> getPatients() {
        return this.jdbcOperations.query(
                "SELECT * FROM PriorityQueueVerbose",
                RepositoryHelper::mapToPatient
        );
    }

    @Override
    public List<Patient> getPatientsFor(int emergencyTeamID) {
        return this.jdbcOperations.query(
                "SELECT * FROM PriorityQueueVerbose WHERE \"emergencyTeamID\" = ?",
                RepositoryHelper::mapToPatient,
                emergencyTeamID
        );
    }

    @Override
    public List<Patient> getPatientsFor(String emergencyTeam) {
        return this.jdbcOperations.query(
                "SELECT * FROM PriorityQueueVerbose WHERE \"emergencyTeam\" = ?",
                RepositoryHelper::mapToPatient,
                emergencyTeam
        );
    }

    @Override
    public List<EmergencyTeam> getEmergencyTeamsCompetentToDealWith(int medicalIssueID) {
        return this.jdbcOperations.query(
                "SELECT EmergencyTeam.* FROM EmergencyTeam INNER JOIN CompetentToDealWith ON EmergencyTeam.\"ID\" = CompetentToDealWith.\"emergencyTeamID\" WHERE \"medicalIssueID\" = ?",
                RepositoryHelper::mapToEmergencyTeam,
                medicalIssueID
        );
    }

    @Override
    public void addPatient(Patient patient) {
        this.jdbcOperations.update(
                "INSERT INTO Patient(\"firstName\", \"lastName\", sex, age, \"priorityID\", \"emergencyTeamID\", \"medicalIssueID\") VALUES (?, ?, ?, ?, ?, ?, ?)",
                patient.getFirstName(),
                patient.getLastName(),
                patient.getSex(),
                patient.getAge(),
                patient.getPriority().getID(),
                patient.getEmergencyTeam().getID(),
                patient.getMedicalIssue().getID()
        );
    }

    @Override
    public void provideDrugsFor(Patient patient) {
        Arrays.stream(patient.getDrugs()).forEach(drug -> this.jdbcOperations.update(
                "INSERT INTO ProvidedDrug(\"patientID\", \"drugID\") VALUES(?, ?)",
                patient.getID(),
                drug.getID()
        ));
    }

    @Override
    public void utilizeMedicalProceduresFor(Patient patient) {
        Arrays.stream(patient.getMedicalProcedures()).forEach(medicalProcedure -> this.jdbcOperations.update(
                "INSERT INTO UtilizedProcedure(\"patientID\", \"medicalProcedureID\") SELECT ?, ? WHERE EXISTS " +
                        "(SELECT * FROM PriorityQueue INNER JOIN MedicalProcedure " +
                        "ON PriorityQueue.\"medicalIssueID\" = MedicalProcedure.\"medicalIssueID\" " +
                        "WHERE PriorityQueue.\"ID\" = ? AND MedicalProcedure.\"ID\" = ?)",
                patient.getID(),
                medicalProcedure.getID(),
                patient.getID(),
                medicalProcedure.getID()
        ));
    }

    @Override
    public void updateOutcomeFor(Patient patient) {
        this.jdbcOperations.update(
                "UPDATE Treatment SET outcome = ? WHERE \"patientID\" = ?",
                patient.getOutcome().getName(),
                patient.getID()
        );
    }

    @Override
    public void removePatient(Patient patient) {
        this.jdbcOperations.update(
                "DELETE FROM Patient WHERE \"ID\" = ?",
                patient.getID()
        );
    }
}
