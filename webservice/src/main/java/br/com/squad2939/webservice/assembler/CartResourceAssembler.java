package br.com.squad2939.webservice.assembler;

import br.com.squad2939.webservice.controller.CartController;
import br.com.squad2939.webservice.model.Cart;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CartResourceAssembler implements RepresentationModelAssembler<Cart, EntityModel<Cart>> {

    @Override
    public EntityModel<Cart> toModel(Cart entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(CartController.class).one(entity.getId())).withSelfRel());
    }
}
