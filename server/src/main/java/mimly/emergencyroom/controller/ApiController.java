package mimly.emergencyroom.controller;

import lombok.extern.slf4j.Slf4j;
import mimly.emergencyroom.aspect.WrapMeInArray;
import mimly.emergencyroom.model.dto.DTO;
import mimly.emergencyroom.model.dto.Patient;
import mimly.emergencyroom.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j(topic = " ** API CONTROLLER ** ")
public class ApiController {

    @Autowired
    private ApiService apiService;

    //    @Secured({"triageNurse"})
    @GetMapping(value = "/priorities", produces = "application/json")
    public List<? extends DTO> getPriorities() {
        return this.apiService.getPriorities();
    }

    @WrapMeInArray
    @GetMapping(value = "/priorities/{param}", produces = "application/json")
    public Object getPriority(@PathVariable @NotNull String param) {
        return this.apiService.getPriority(param);
    }

    @GetMapping(value = "/medical-issues", produces = "application/json")
    public List<? extends DTO> getMedicalIssues() {
        return apiService.getMedicalIssues();
    }

    @WrapMeInArray
    @GetMapping(value = "/medical-issues/{param}", produces = "application/json")
    public Object getMedicalIssue(@PathVariable @NotNull String param) {
        return apiService.getMedicalIssue(param);
    }

    @GetMapping(value = "/drugs", produces = "application/json")
    public List<? extends DTO> getDrugs() {
        return apiService.getDrugs();
    }

    @WrapMeInArray
    @GetMapping(value = "/drugs/{param}", produces = "application/json")
    public Object getDrug(@PathVariable @NotNull String param) {
        return apiService.getDrug(param);
    }

    @GetMapping(value = "/medical-procedures", produces = "application/json")
    public List<? extends DTO> getMedicalProcedures() {
        return apiService.getMedicalProcedures();
    }

    @WrapMeInArray
    @GetMapping(value = "/medical-procedures/{param}", produces = "application/json")
    public Object getMedicalProcedure(@PathVariable @NotNull String param) {
        return apiService.getMedicalProcedure(param);
    }

    @GetMapping(value = "/medical-procedures/for/{param}", produces = "application/json")
    public List<? extends DTO> getMedicalProceduresFor(@PathVariable @NotNull String param) {
        return apiService.getMedicalProceduresFor(param);
    }

    @GetMapping(value = "/outcomes", produces = "application/json")
    public List<? extends DTO> getOutcomes() {
        return apiService.getOutcomes();
    }

    @GetMapping(value = "/emergency-teams", produces = "application/json")
    public List<? extends DTO> getEmergencyTeams() {
        return apiService.getEmergencyTeams();
    }

    @WrapMeInArray
    @GetMapping(value = "/emergency-teams/{param}", produces = "application/json")
    public Object getEmergencyTeam(@PathVariable @NotNull String param) {
        return apiService.getEmergencyTeam(param);
    }

    @GetMapping(value = "/patients", produces = "application/json")
    public List<? extends DTO> getPatients() {
        return apiService.getPatients();
    }

    @GetMapping(value = "/emergency-teams/{param}/patients", produces = "application/json")
    public List<? extends DTO> getPatientsFor(@PathVariable @NotNull String param) {
        return apiService.getPatientsFor(param);
    }

    @GetMapping(value = "/emergency-teams/competent-to-deal-with/{param}", produces = "application/json")
    public List<? extends DTO> getEmergencyTeamsCompetentToDealWith(@PathVariable @NotNull String param) {
        return apiService.getEmergencyTeamsCompetentToDealWith(param);
    }

    @PostMapping(value = "/patients", consumes = "application/json")
    public Object enqueuePatient(@RequestBody @NotNull Patient patient) {
        apiService.enqueuePatient(patient);
        return new Object[]{};
    }

    @DeleteMapping(value = "/patients/{param}", consumes = "application/json")
    public Object dequeuePatient(@RequestBody @NotNull Patient patient, @PathVariable @NotNull String param) {
        apiService.dequeuePatient(patient);
        return new Object[]{};
    }
}
