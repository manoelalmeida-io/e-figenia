package br.com.squad2939.webservice.model;

import br.com.squad2939.webservice.dto.cart.CartResponseDto;
import br.com.squad2939.webservice.dto.product.ProductResponseDto;
import br.com.squad2939.webservice.model.key.CartProductKey;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Optional;

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

    public CartResponseDto getCart() {
        ModelMapper mapper = new ModelMapper();
        Optional<Cart> cart = Optional.ofNullable(this.cart);

        if (cart.isPresent())
            return mapper.map(cart, CartResponseDto.class);

        return null;
    }

    public ProductResponseDto getProduct() {
        ModelMapper mapper = new ModelMapper();
        Optional<Product> product = Optional.ofNullable(this.product);

        if (product.isPresent())
            return mapper.map(product, ProductResponseDto.class);

        return null;
    }
}
