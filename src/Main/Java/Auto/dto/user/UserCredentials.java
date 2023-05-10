package Auto.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCredentials {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
