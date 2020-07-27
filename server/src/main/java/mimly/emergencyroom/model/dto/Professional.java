package mimly.emergencyroom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Professional implements DTO {

    @JsonProperty("ID")
    Integer ID;
    String role;
    String username;
    String password;
}
