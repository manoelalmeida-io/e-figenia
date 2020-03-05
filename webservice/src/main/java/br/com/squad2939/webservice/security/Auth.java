package br.com.squad2939.webservice.security;

import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class Auth {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    static final Logger logger = LoggerFactory.getLogger(Auth.class);

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
}
