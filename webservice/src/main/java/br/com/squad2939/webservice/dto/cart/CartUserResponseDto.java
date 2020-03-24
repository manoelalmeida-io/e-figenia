package br.com.squad2939.webservice.dto.cart;

import br.com.squad2939.webservice.controller.UserController;
import br.com.squad2939.webservice.dto.user.UserResponseDto;
import br.com.squad2939.webservice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Getter @Setter
public class CartUserResponseDto {
    private Long id;
    private Boolean finished;
    private User user;

    public EntityModel<UserResponseDto> getUser() {
        ModelMapper mapper = new ModelMapper();
        return new EntityModel<>(mapper.map(user, UserResponseDto.class),
                linkTo(methodOn(UserController.class).one(null, user.getId())).withSelfRel());
    }
}
