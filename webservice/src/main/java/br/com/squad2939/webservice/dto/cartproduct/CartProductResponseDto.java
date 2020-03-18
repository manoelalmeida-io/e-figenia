package br.com.squad2939.webservice.dto.cartproduct;

import br.com.squad2939.webservice.dto.product.ProductResponseDto;
import br.com.squad2939.webservice.model.Product;
import br.com.squad2939.webservice.model.key.CartProductKey;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class CartProductResponseDto {
    CartProductKey id;
    Product product;
    int qty;

    public ProductResponseDto getProduct() {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(product, ProductResponseDto.class);
    }
}
