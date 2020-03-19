package br.com.squad2939.webservice.dto.cartproduct;

import br.com.squad2939.webservice.controller.ProductController;
import br.com.squad2939.webservice.dto.product.ProductResponseDto;
import br.com.squad2939.webservice.model.Product;
import br.com.squad2939.webservice.model.key.CartProductKey;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Getter @Setter
public class CartProductResponseDto {
    CartProductKey id;
    Product product;
    int qty;

    public EntityModel<ProductResponseDto> getProduct() {
        ModelMapper mapper = new ModelMapper();
        return new EntityModel<>(mapper.map(product, ProductResponseDto.class),
                linkTo(methodOn(ProductController.class).one(product.getId())).withSelfRel());
    }
}
