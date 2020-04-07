package br.com.squad2939.backend.model;

import br.com.squad2939.backend.model.key.CartProductKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CartProduct {
    @EmbeddedId
    private CartProductKey id;

    @ManyToOne
    @MapsId(value = "cart_id")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @MapsId(value = "product_id")
    @JoinColumn(name = "product_id")
    private Product product;
}
