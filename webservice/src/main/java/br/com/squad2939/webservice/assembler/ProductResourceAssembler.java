package br.com.squad2939.webservice.assembler;

import br.com.squad2939.webservice.controller.ProductController;
import br.com.squad2939.webservice.model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductResourceAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ProductController.class).one(entity.getId())).withSelfRel());
    }
}
