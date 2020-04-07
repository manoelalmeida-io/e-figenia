package br.com.squad2939.backend.service;

import br.com.squad2939.backend.exception.ResourceNotFoundException;
import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

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
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            updated.setId(user.get().getId());
            return repository.save(updated);
        }

        throw new ResourceNotFoundException(id);
    }

    public void delete(Long id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent())
            repository.delete(user.get());
        else
            throw new ResourceNotFoundException(id);
    }
}
