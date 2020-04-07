package br.com.squad2939.backend.model;

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
    private List<CartProduct> carts;
}
