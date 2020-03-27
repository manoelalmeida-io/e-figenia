package br.com.squad2939.backend.service;

import br.com.squad2939.backend.exception.ForeignKeyConstraintException;
import br.com.squad2939.backend.exception.ResourceNotFoundException;
import br.com.squad2939.backend.model.Cart;
import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final UserService userService;

    public List<Cart> all() {
        return repository.findAll();
    }

    public Optional<Cart> one(Long id) {
        return repository.findById(id);
    }

    public Cart create(Cart cart) {
        Optional<User> user = userService.one(cart.getUser().getId());

        if (user.isPresent()) {
            cart.setUser(user.get());
            return repository.save(cart);
        }

        throw new ForeignKeyConstraintException();
    }

    public Cart update(Long id, Cart updated) {
        Optional<Cart> cart = repository.findById(id);

        if (cart.isPresent()) {
            Optional<User> user = userService.one(updated.getUser().getId());
            updated.setUser(user.orElseThrow(ForeignKeyConstraintException::new));
            updated.setId(id);
            return repository.save(updated);
        }

        throw new ResourceNotFoundException(id);
    }

    public void delete(Long id) {
        Optional<Cart> cart = repository.findById(id);
        if (cart.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException(id);
    }
}
