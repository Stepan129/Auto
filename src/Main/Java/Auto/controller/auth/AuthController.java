package Auto.controller.auth;

import Auto.annotation.Auth;
import Auto.dto.user.UserCredentials;
import Auto.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void login(@RequestBody @Valid UserCredentials userCredentials) {
        authService.login(userCredentials);
    }

    @Auth
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("refresh-token")
    public void refreshToken() {
    }
}
