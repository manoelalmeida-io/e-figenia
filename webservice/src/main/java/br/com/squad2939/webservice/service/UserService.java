package br.com.squad2939.webservice.service;

import br.com.squad2939.webservice.dto.user.UserRequestDto;
import br.com.squad2939.webservice.dto.user.UserTokenRequestDto;
import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.repository.UserRepository;
import br.com.squad2939.webservice.security.AccountCredentials;
import br.com.squad2939.webservice.security.Auth;
import br.com.squad2939.webservice.security.TokenAuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper mapper = new ModelMapper();

    public List<User> list() {
        return repository.findAll();
    }

    public User create(UserRequestDto newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = mapper.map(newUser, User.class);

        try {
            return repository.save(user);
        } catch (Exception ex) {
            return null;
        }
    }

    public User auth(HttpServletResponse response, AccountCredentials credentials) {
        Auth auth = new Auth(repository, passwordEncoder);

        return auth.authenticate(response, credentials);
    }

    public Optional<User> authToken(UserTokenRequestDto tokenRequestDto) {
        Auth auth = new Auth(repository, passwordEncoder);

        return auth.authenticateToken(tokenRequestDto.getToken());
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }
}
