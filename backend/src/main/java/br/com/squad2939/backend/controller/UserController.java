package br.com.squad2939.backend.controller;

import br.com.squad2939.backend.exception.ResourceNotFoundException;
import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/users")
    public ResponseEntity<?> all() {
        List<User> users = service.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        Optional<User> user = service.one(id);

        if (user.isPresent())
            return ResponseEntity.ok(user.get());

        throw new ResourceNotFoundException(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        User created = service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {
        User updated = service.update(id, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
