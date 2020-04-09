package br.com.squad2939.backend.model;

import br.com.squad2939.backend.serialize.ProductCartListSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "product")
    @JsonSerialize(using = ProductCartListSerializer.class)
    private List<CartProduct> carts;
}
