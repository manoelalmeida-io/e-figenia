package br.com.squad2939.webservice.model;

import br.com.squad2939.webservice.dto.cartproduct.ProductCartResponseDto;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer qtyStock;
    private String specs;
    @OneToMany(mappedBy = "product")
    private Set<CartProduct> carts;

    public Set<ProductCartResponseDto> getCarts() {
        ModelMapper mapper = new ModelMapper();

        Optional<Set<CartProduct>> carts = Optional.ofNullable(this.carts);

        return carts.map(cartProducts -> cartProducts.stream()
                .map(cartProduct -> mapper.map(cartProduct, ProductCartResponseDto.class))
                .collect(Collectors.toSet())).orElseGet(HashSet::new);
    }
}
