package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.assembler.UserResponseResourceAssembler;
import br.com.squad2939.webservice.dto.user.UserCreateRequestDto;
import br.com.squad2939.webservice.dto.user.UserResponseDto;
import br.com.squad2939.webservice.dto.user.UserTokenRequestDto;
import br.com.squad2939.webservice.exception.CannotCreateResourceException;
import br.com.squad2939.webservice.exception.CannotFindResourceException;
import br.com.squad2939.webservice.exception.LoginException;
import br.com.squad2939.webservice.exception.UserHasNotAdministrativePrivilegesException;
import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.security.AccountCredentials;
import br.com.squad2939.webservice.security.AuthType;
import br.com.squad2939.webservice.security.Authorization;
import br.com.squad2939.webservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserResponseResourceAssembler assembler;
    private ModelMapper mapper = new ModelMapper();

    @GetMapping("/users")
    public ResponseEntity<?> all(@RequestHeader("authorization") String token) {
        if (!Authorization.hasAuthorization(token, AuthType.ADMIN)) {
            throw new UserHasNotAdministrativePrivilegesException();
        }

        List<User> users = service.list();
        List<UserResponseDto> dtoList = users.stream()
                .map(user -> mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserCreateRequestDto newUser) {
        Optional<User> user = Optional.ofNullable(service.create(newUser));

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } else {
            throw new CannotCreateResourceException("Error creating user");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AccountCredentials credentials, HttpServletResponse response) {
        Optional<User> user = Optional.ofNullable(service.auth(response, credentials));

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);
            return ResponseEntity.ok(dto);
        }

        throw new LoginException("Invalid email or password");
    }

    @PostMapping("/auth/token")
    public ResponseEntity<?> authToken(@RequestBody UserTokenRequestDto tokenRequestDto) {
        Optional<User> user = service.authToken(tokenRequestDto);

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);
            return ResponseEntity.ok(dto);
        }

        throw new LoginException("Invalid token");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> one(@RequestHeader HttpHeaders headers, @PathVariable Long id) {
        if (!Authorization.hasAuthorization(headers.getFirst("authorization"), AuthType.ADMIN)) {
            throw new UserHasNotAdministrativePrivilegesException();
        }

        Optional<User> user = service.get(id);

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);
            return ResponseEntity.ok(dto);
        }

        throw new CannotFindResourceException(id);
    }
}
