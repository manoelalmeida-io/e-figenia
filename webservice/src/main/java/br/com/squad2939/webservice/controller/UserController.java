package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.dto.ErrorDto;
import br.com.squad2939.webservice.dto.UserDto;
import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.security.AccountCredentials;
import br.com.squad2939.webservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;
    private ModelMapper mapper = new ModelMapper();

    @GetMapping("/users")
    public List<User> all() {
        return service.list();
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User newUser) {
        var user = service.create(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AccountCredentials credentials, HttpServletResponse response) {
        Optional<User> user = Optional.ofNullable(service.auth(response, credentials));

        if (user.isPresent()) {
            UserDto dto = mapper.map(user.get(), UserDto.class);

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("Invalid email or password"));
    }
}
