package br.com.squad2939.webservice.assembler;

import br.com.squad2939.webservice.controller.CartController;
import br.com.squad2939.webservice.dto.cart.CartUserResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CartUserResourceAssembler
        implements RepresentationModelAssembler<CartUserResponseDto, EntityModel<CartUserResponseDto>> {

    @Override
    public EntityModel<CartUserResponseDto> toModel(CartUserResponseDto entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(CartController.class).one(entity.getId())).withSelfRel());
    }
}
