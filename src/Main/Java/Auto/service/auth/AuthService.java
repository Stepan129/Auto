package Auto.service.auth;


import Auto.annotation.Auth;
import Auto.dto.user.UserCredentials;

public interface AuthService {
    void login(UserCredentials userCredentials);

    void verifyAuthority(Auth auth);

    //void login(UserCredentials userCredentials );


}
