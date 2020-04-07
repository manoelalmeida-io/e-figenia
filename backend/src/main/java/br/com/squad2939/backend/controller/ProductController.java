package br.com.squad2939.backend.controller;

import br.com.squad2939.backend.exception.ResourceNotFoundException;
import br.com.squad2939.backend.model.Product;
import br.com.squad2939.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/products")
    private ResponseEntity<?> all() {
        List<Product> products = service.all();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    private ResponseEntity<?> one(@PathVariable Long id) {
        Optional<Product> product = service.one(id);

        if (product.isPresent())
            return ResponseEntity.ok(product);

        throw new ResourceNotFoundException(id);
    }

    @PostMapping("/products")
    private ResponseEntity<?> create(@RequestBody Product product) {
        Product created = service.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/products/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.update(id, product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/products/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
