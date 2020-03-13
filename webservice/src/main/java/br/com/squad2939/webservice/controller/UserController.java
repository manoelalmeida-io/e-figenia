package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.assembler.UserResponseResourceAssembler;
import br.com.squad2939.webservice.dto.ErrorDto;
import br.com.squad2939.webservice.dto.user.UserRequestDto;
import br.com.squad2939.webservice.dto.user.UserResponseDto;
import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.security.AccountCredentials;
import br.com.squad2939.webservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserResponseResourceAssembler assembler;
    private ModelMapper mapper = new ModelMapper();

    @GetMapping("/users")
    public CollectionModel<EntityModel<UserResponseDto>> all() {
        List<User> users = service.list();
        List<EntityModel<UserResponseDto>> dtoList = users.stream()
                .map(user -> assembler.toModel(mapper.map(user, UserResponseDto.class)))
                .collect(Collectors.toList());

        return new CollectionModel<>(dtoList,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserRequestDto newUser) {
        Optional<User> user = Optional.ofNullable(service.create(newUser));

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);
            EntityModel<UserResponseDto> entityModel = assembler.toModel(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("Não foi possível criar o usuário"));
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AccountCredentials credentials, HttpServletResponse response) {
        Optional<User> user = Optional.ofNullable(service.auth(response, credentials));

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);
            EntityModel<UserResponseDto> entityModel = assembler.toModel(dto);

            return ResponseEntity.ok(entityModel);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("Invalid email or password"));
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        Optional<User> user = service.get(id);

        if (user.isPresent()) {
            UserResponseDto dto = mapper.map(user.get(), UserResponseDto.class);
            EntityModel<UserResponseDto> entityModel = assembler.toModel(dto);

            return ResponseEntity.ok(entityModel);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("Invalid user"));
    }
}
