package br.com.squad2939.backend.service;

import br.com.squad2939.backend.model.Cart;
import br.com.squad2939.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;

    public List<Cart> all() {
        return repository.findAll();
    }

    public Optional<Cart> one(Long id) {
        return repository.findById(id);
    }

    public Cart create(Cart cart) {
        return repository.save(cart);
    }

    public Cart update(Long id, Cart cart) {
        cart.setId(id);
        return repository.save(cart);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
