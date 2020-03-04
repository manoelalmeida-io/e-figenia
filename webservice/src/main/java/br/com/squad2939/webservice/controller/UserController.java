package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> all() {
        return service.list();
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User newUser) {
        var user = service.create(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
