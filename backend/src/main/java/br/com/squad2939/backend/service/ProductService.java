package br.com.squad2939.backend.service;

import br.com.squad2939.backend.exception.ResourceNotFoundException;
import br.com.squad2939.backend.model.Product;
import br.com.squad2939.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> all() {
        return repository.findAll();
    }

    public Optional<Product> one(Long id) {
        return repository.findById(id);
    }

    public Product create(Product newProduct) {
        return repository.save(newProduct);
    }

    public Product update(Long id, Product updated) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            updated.setId(product.get().getId());
            return repository.save(updated);
        }

        throw new ResourceNotFoundException(id);
    }

    public void delete(Long id) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent())
            repository.delete(product.get());
        else
            throw new ResourceNotFoundException(id);
    }
}
