package br.com.squad2939.webservice.service;

import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.repository.UserRepository;
import br.com.squad2939.webservice.security.AccountCredentials;
import br.com.squad2939.webservice.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository repository;
    @Autowired private PasswordEncoder passwordEncoder;

    public List<User> list() {
        return repository.findAll();
    }

    public User create(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return repository.save(newUser);
    }

    public User auth(HttpServletResponse response, AccountCredentials credentials) {
        Auth auth = new Auth(repository, passwordEncoder);

        return auth.authenticate(response, credentials);
    }
}
