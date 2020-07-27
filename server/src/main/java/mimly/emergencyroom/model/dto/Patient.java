package mimly.emergencyroom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Patient implements DTO {

    Integer place;
    @JsonProperty("ID")
    Integer ID;
    String firstName;
    String lastName;
    String sex;
    Integer age;
    Priority priority;
    EmergencyTeam emergencyTeam;
    Integer emergencyRoomID;
    String arrival;
    MedicalIssue medicalIssue;
    Integer waitingTime;
    Integer hadToWait;
    Drug[] drugs;
    MedicalProcedure[] medicalProcedures;
    Outcome outcome;
}
