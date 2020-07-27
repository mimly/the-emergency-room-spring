package mimly.emergencyroom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Outcome implements DTO {

    @JsonProperty("ID")
    Integer ID;
    String name;
}
