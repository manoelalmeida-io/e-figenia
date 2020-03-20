package br.com.squad2939.webservice.dto.product;

import br.com.squad2939.webservice.dto.cartproduct.ProductCartResponseDto;
import br.com.squad2939.webservice.model.CartProduct;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private Double price;
    private Integer qtyStock;
    private String specs;
    private Set<CartProduct> carts;

    public Set<ProductCartResponseDto> getCarts() {
        ModelMapper mapper = new ModelMapper();
        Optional<Set<CartProduct>> carts = Optional.ofNullable(this.carts);

        return carts.map(cartProducts -> cartProducts.stream()
                .map(cartProduct -> mapper.map(cartProduct, ProductCartResponseDto.class))
                .collect(Collectors.toSet())).orElseGet(HashSet::new);
    }
}
