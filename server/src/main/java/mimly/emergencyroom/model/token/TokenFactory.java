package mimly.emergencyroom.model.token;

public class TokenFactory {

    public static Token getAuthorizedToken(String role, String username) {
        return new Token(true, 200, "OK", role, username);
    }

    public static Token getUnauthorizedToken(int status, String error) {
        return new Token(false, status, error, null, null);
    }
}
