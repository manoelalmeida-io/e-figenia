package br.com.squad2939.backend.repository;

import br.com.squad2939.backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
