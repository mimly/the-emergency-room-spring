package mimly.emergencyroom.model.token;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class Token {

    @NotNull Boolean authenticated;
    @NotNull Integer status;
    @NotNull String error;
    String role;
    String username;
}
