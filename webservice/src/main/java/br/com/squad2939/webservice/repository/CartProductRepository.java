package br.com.squad2939.webservice.repository;

import br.com.squad2939.webservice.model.CartProduct;
import br.com.squad2939.webservice.model.key.CartProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {
}
