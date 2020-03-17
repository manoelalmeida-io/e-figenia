package br.com.squad2939.webservice.model;

import br.com.squad2939.webservice.model.key.CartProductKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartProduct {
    @EmbeddedId
    CartProductKey id;

    @ManyToOne
    @MapsId("cart_id")
    @JoinColumn(name = "cart_id")
    Cart cart;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;

    int qty;
}
