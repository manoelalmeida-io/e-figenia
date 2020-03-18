package br.com.squad2939.webservice.assembler;

import br.com.squad2939.webservice.controller.ProductController;
import br.com.squad2939.webservice.dto.product.ProductResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductResponseResourceAssembler implements
        RepresentationModelAssembler<ProductResponseDto, EntityModel<ProductResponseDto>> {

    @Override
    public EntityModel<ProductResponseDto> toModel(ProductResponseDto entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ProductController.class).one(entity.getId())).withSelfRel());
    }
}
