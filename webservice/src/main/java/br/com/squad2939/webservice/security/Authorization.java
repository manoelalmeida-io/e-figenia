package br.com.squad2939.webservice.security;

public class Authorization {

    public static Boolean hasAuthorization(String token, AuthType authType) {
        switch (authType) {
            case ADMIN:
                return hasAdmin(token);
            case OWNER:
                return hasOwner();
            case OWNER_ADMIN:
                return hasAdmin(token) || hasOwner();
            default:
                return false;
        }
    }

    private static Boolean hasAdmin(String token) {
        return TokenAuthenticationService.isAdmin(token);
    }

    private static Boolean hasOwner() {
        return true;
    }
}
