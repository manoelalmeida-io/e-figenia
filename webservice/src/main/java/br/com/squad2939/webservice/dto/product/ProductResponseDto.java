package br.com.squad2939.webservice.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private Double price;
    private Integer qtyStock;
    private String specs;
}
