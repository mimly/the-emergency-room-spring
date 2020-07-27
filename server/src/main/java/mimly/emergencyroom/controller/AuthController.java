package mimly.emergencyroom.controller;

import lombok.extern.slf4j.Slf4j;
import mimly.emergencyroom.aspect.WrapMeInArray;
import mimly.emergencyroom.model.LoginFormData;
import mimly.emergencyroom.model.token.TokenFactory;
import mimly.emergencyroom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j(topic = "** AUTH CONTROLLER ** ")
public class AuthController {

    @Autowired
    private AuthService authService;

    @WrapMeInArray
    @GetMapping(value = "/isAuthenticated", produces = "application/json")
    public Object isAuthenticated(HttpSession httpSession) {
        if (this.authService.isAuthenticated(httpSession)) {
            return TokenFactory.getAuthorizedToken(
                    httpSession.getAttribute("role").toString(),
                    httpSession.getAttribute("username").toString()
            );
        }

        log.warn("Unauthorized");
        return TokenFactory.getUnauthorizedToken(401, "Unauthorized");
    }

    @WrapMeInArray
    @PostMapping(value = "/setSession", produces = "application/json")
    public Object setSession(@Valid @RequestBody LoginFormData loginFormData, Errors errors, HttpSession httpSession) {
        log.debug(loginFormData.toString());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(err -> log.warn(err.toString()));
            return TokenFactory.getUnauthorizedToken(401, "Unauthorized");
        }

        if (!this.authService.isUsernameCorrect(loginFormData.getUsername())) {
            log.warn("Username Incorrect");
            return TokenFactory.getUnauthorizedToken(401, "Username Incorrect");
        }

        if (!this.authService.isPasswordCorrect(loginFormData.getPassword())) {
            log.warn("Password Incorrect");
            return TokenFactory.getUnauthorizedToken(401, "Password Incorrect");
        }

        this.authService.setSession(httpSession);
        return TokenFactory.getAuthorizedToken(
                httpSession.getAttribute("role").toString(),
                httpSession.getAttribute("username").toString()
        );
    }

    @WrapMeInArray
    @PutMapping(value = "/unsetSession", produces = "application/json")
    public Object unsetSession(HttpSession httpSession) {
        this.authService.unsetSession(httpSession);
        return TokenFactory.getUnauthorizedToken(401, "Unauthorized");
    }
}
