package br.com.squad2939.webservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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
    Set<CartProduct> carts;
}
