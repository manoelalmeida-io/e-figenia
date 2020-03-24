package br.com.squad2939.backend.controller;

import br.com.squad2939.backend.exception.ResourceNotFoundException;
import br.com.squad2939.backend.model.Cart;
import br.com.squad2939.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService service;

    @GetMapping("/carts")
    private ResponseEntity<?> all() {
        List<Cart> carts = service.all();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/carts/{id}")
    private ResponseEntity<?> one(@PathVariable Long id) {
        Optional<Cart> cart = service.one(id);

        if (cart.isPresent())
            return ResponseEntity.ok(cart.get());

        throw new ResourceNotFoundException(id);
    }

    @PostMapping("/carts")
    private ResponseEntity<?> create(@RequestBody Cart newCart) {
        Cart cart = service.create(newCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @PutMapping("/carts/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cart cart) {
        Cart updated = service.update(id, cart);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/carts/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
