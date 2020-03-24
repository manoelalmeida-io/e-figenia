package br.com.squad2939.backend.service;

import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> all() {
        return repository.findAll();
    }

    public Optional<User> one(Long id) {
        return repository.findById(id);
    }

    public User create(User newUser) {
        return repository.save(newUser);
    }

    public User update(Long id, User updated) {
        updated.setId(id);
        return repository.save(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
