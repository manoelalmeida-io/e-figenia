package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.model.Cart;
import br.com.squad2939.webservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("/carts")
    public List<Cart> all() {
        return service.list();
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> create(@RequestBody Cart newCart) {
        Cart cart = service.create(newCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }
}
