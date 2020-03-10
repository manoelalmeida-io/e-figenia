package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.dto.ErrorDto;
import br.com.squad2939.webservice.dto.user.UserRequestDto;
import br.com.squad2939.webservice.dto.user.UserResponseDto;
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
    public ResponseEntity<?> create(@RequestBody UserRequestDto newUser) {
        Optional<User> user = Optional.ofNullable(service.create(newUser));

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("Não foi possível criar o usuário"));
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AccountCredentials credentials, HttpServletResponse response) {
        Optional<User> user = Optional.ofNullable(service.auth(response, credentials));

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("Invalid email or password"));
    }
}
