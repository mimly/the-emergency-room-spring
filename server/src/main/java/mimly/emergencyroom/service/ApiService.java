package mimly.emergencyroom.service;

import lombok.extern.slf4j.Slf4j;
import mimly.emergencyroom.model.dto.*;
import mimly.emergencyroom.repository.dao.Repository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j(topic = " ** API SERVICE ** ")
public class ApiService {

    @Autowired
    private Repository repository;

    public List<Priority> getPriorities() {
        return repository.getPriorities();
    }

    public Priority getPriority(String param) {
        return StringUtils.isNumeric(param)
                ? repository.getPriorityByID(Integer.parseInt(param))
                : repository.getPriorityByName(param);
    }

    public List<MedicalIssue> getMedicalIssues() {
        return repository.getMedicalIssues();
    }

    public MedicalIssue getMedicalIssue(String param) {
        return StringUtils.isNumeric(param)
                ? repository.getMedicalIssueByID(Integer.parseInt(param))
                : repository.getMedicalIssueByName(param);
    }

    public List<Drug> getDrugs() {
        return repository.getDrugs();
    }

    public Drug getDrug(String param) {
        return StringUtils.isNumeric(param)
                ? repository.getDrugByID(Integer.parseInt(param))
                : repository.getDrugByName(param);
    }

    public List<MedicalProcedure> getMedicalProcedures() {
        return repository.getMedicalProcedures();
    }

    public MedicalProcedure getMedicalProcedure(String param) {
        return StringUtils.isNumeric(param)
                ? repository.getMedicalProcedureByID(Integer.parseInt(param))
                : repository.getMedicalProcedureByName(param);
    }

    public List<MedicalProcedure> getMedicalProceduresFor(String param) {
        MedicalIssue medicalIssue = this.getMedicalIssue(param);
        return repository.getMedicalProceduresFor(medicalIssue.getID());
    }

    public List<Outcome> getOutcomes() {
        return repository.getOutcomes();
    }

    public List<EmergencyTeam> getEmergencyTeams() {
        return repository.getEmergencyTeams();
    }

    public EmergencyTeam getEmergencyTeam(String param) {
        return StringUtils.isNumeric(param)
                ? repository.getEmergencyTeamByID(Integer.parseInt(param))
                : repository.getEmergencyTeamByName(param);
    }

    public List<Patient> getPatients() {
        return repository.getPatients();
    }

    public List<Patient> getPatientsFor(String param) {
        return StringUtils.isNumeric(param)
                ? repository.getPatientsFor(Integer.parseInt(param))
                : repository.getPatientsFor(param);
    }

    public List<EmergencyTeam> getEmergencyTeamsCompetentToDealWith(String param) {
        MedicalIssue medicalIssue = this.getMedicalIssue(param);
        return repository.getEmergencyTeamsCompetentToDealWith(medicalIssue.getID());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void enqueuePatient(Patient patient) {
        log.error(patient.toString());
        repository.addPatient(patient);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void dequeuePatient(Patient patient) {
        log.error(patient.toString());
        repository.provideDrugsFor(patient);
        repository.utilizeMedicalProceduresFor(patient);
        repository.updateOutcomeFor(patient);
        repository.removePatient(patient);
    }
}
