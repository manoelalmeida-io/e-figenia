package br.com.squad2939.webservice.service;

import br.com.squad2939.webservice.model.Cart;
import br.com.squad2939.webservice.model.Product;
import br.com.squad2939.webservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> all() {
        return repository.findAll();
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Optional<Product> one(Long id) {
        return repository.findById(id);
    }
}
