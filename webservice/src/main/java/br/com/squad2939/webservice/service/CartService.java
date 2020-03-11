package br.com.squad2939.webservice.service;

import br.com.squad2939.webservice.model.Cart;
import br.com.squad2939.webservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository repository;

    public List<Cart> list() {
        return repository.findAll();
    }

    public Cart create(Cart cart) {
        return repository.save(cart);
    }
}
