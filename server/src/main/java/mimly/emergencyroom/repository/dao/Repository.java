package mimly.emergencyroom.repository.dao;

import mimly.emergencyroom.model.dto.*;

import java.util.List;

public interface Repository {

    Professional getProfessional(String username);

    List<Priority> getPriorities();

    Priority getPriorityByName(String name);

    Priority getPriorityByID(int ID);

    List<MedicalIssue> getMedicalIssues();

    MedicalIssue getMedicalIssueByName(String name);

    MedicalIssue getMedicalIssueByID(int ID);

    List<Drug> getDrugs();

    Drug getDrugByName(String name);

    Drug getDrugByID(int ID);

    List<MedicalProcedure> getMedicalProcedures();

    MedicalProcedure getMedicalProcedureByName(String name);

    MedicalProcedure getMedicalProcedureByID(int ID);

    List<MedicalProcedure> getMedicalProceduresFor(int medicalIssueID);

    List<Outcome> getOutcomes();

    List<EmergencyTeam> getEmergencyTeams();

    EmergencyTeam getEmergencyTeamByName(String name);

    EmergencyTeam getEmergencyTeamByID(int ID);

    List<Patient> getPatients();

    List<Patient> getPatientsFor(int emergencyTeamID);

    List<Patient> getPatientsFor(String emergencyTeam);

    List<EmergencyTeam> getEmergencyTeamsCompetentToDealWith(int medicalIssueID);

    void addPatient(Patient patient);

    void provideDrugsFor(Patient patient);

    void utilizeMedicalProceduresFor(Patient patient);

    void updateOutcomeFor(Patient patient);

    void removePatient(Patient patient);
}
