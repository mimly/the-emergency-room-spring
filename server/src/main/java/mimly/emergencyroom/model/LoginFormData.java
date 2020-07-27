package mimly.emergencyroom.model;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class LoginFormData {

    @NotNull
    @Size(min = 4, max = 32) String username;
    @NotNull
    @Size(min = 4, max = 32) String password;
}
