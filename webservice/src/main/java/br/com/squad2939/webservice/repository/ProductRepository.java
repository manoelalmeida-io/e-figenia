package br.com.squad2939.webservice.repository;

import br.com.squad2939.webservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
