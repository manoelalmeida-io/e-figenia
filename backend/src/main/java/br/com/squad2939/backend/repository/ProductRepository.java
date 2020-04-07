package br.com.squad2939.backend.repository;

import br.com.squad2939.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
