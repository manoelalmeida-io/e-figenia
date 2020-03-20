package br.com.squad2939.webservice.security;

import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class Auth {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public Auth(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(HttpServletResponse response, AccountCredentials credentials) {
        Optional<User> userOptional = Optional.ofNullable(repository.findByEmail(credentials.getEmail()));

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
                TokenAuthenticationService.addAuthentication(response, user.getId());

                return user;
            }
        }

        return null;
    }

    public Optional<User> authenticateToken(String token) {
        Long userId = TokenAuthenticationService.getUserIdFromToken(token);

        if (userId != null)
            return repository.findById(userId);

        return Optional.empty();
    }

    public Boolean isAdmin(String token) {
        Optional<User> user = this.authenticateToken(token);

        if (user.isPresent()) {
            Optional<Boolean> admin = Optional.ofNullable(user.get().getAdmin());
            return admin.orElse(false);
        }

        return false;
    }
}
