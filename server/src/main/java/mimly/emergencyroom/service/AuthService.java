package mimly.emergencyroom.service;

import lombok.extern.slf4j.Slf4j;
import mimly.emergencyroom.model.dto.Professional;
import mimly.emergencyroom.repository.dao.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j(topic = " ** AUTH SERVICE ** ")
public class AuthService {

    @Autowired
    private Repository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Professional professional = null;

    public boolean isAuthenticated(HttpSession httpSession) {
        return httpSession.getAttribute("role") != null && httpSession.getAttribute("username") != null;
    }

    public boolean isUsernameCorrect(String username) {
        try {
            this.professional = this.repository.getProfessional(username);
        } catch (DataAccessException dae) {
            return false;
        }
        return true;
    }

    public boolean isPasswordCorrect(String password) {
        return passwordEncoder.matches(password, this.professional.getPassword());
    }

    public void setSession(HttpSession httpSession) {
        httpSession.setAttribute("ID", this.professional.getID());
        httpSession.setAttribute("role", this.professional.getRole());
        httpSession.setAttribute("username", this.professional.getUsername());
    }

    public void unsetSession(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
