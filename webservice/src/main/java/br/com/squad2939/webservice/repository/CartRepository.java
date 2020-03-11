package br.com.squad2939.webservice.repository;

import br.com.squad2939.webservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
