package br.com.squad2939.webservice.assembler;

import br.com.squad2939.webservice.controller.UserController;
import br.com.squad2939.webservice.dto.user.UserResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserResponseResourceAssembler implements
        RepresentationModelAssembler<UserResponseDto, EntityModel<UserResponseDto>> {

    @Override
    public EntityModel<UserResponseDto> toModel(UserResponseDto entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(UserController.class).one(entity.getId())).withSelfRel());
    }
}
