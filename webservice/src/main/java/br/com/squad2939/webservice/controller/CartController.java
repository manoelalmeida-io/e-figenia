package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.assembler.CartResourceAssembler;
import br.com.squad2939.webservice.assembler.CartUserResourceAssembler;
import br.com.squad2939.webservice.dto.cart.CartUserResponseDto;
import br.com.squad2939.webservice.model.Cart;
import br.com.squad2939.webservice.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService service;
    @Autowired
    private CartResourceAssembler assembler;
    @Autowired
    private CartUserResourceAssembler cartUserAssembler;
    private ModelMapper mapper = new ModelMapper();

    @GetMapping("/carts")
    public CollectionModel<EntityModel<CartUserResponseDto>> all() {
        List<Cart> carts = service.list();
        List<EntityModel<CartUserResponseDto>> cartList = carts.stream()
                .map(cart -> cartUserAssembler.toModel(mapper.map(cart, CartUserResponseDto.class)))
                .collect(Collectors.toList());

        return new CollectionModel<>(cartList,
                linkTo(methodOn(CartController.class).all()).withSelfRel());
    }

    @PostMapping("/carts")
    public ResponseEntity<?> create(@RequestBody Cart newCart) {
        Cart cart = service.create(newCart);
        CartUserResponseDto dto = mapper.map(cart, CartUserResponseDto.class);
        EntityModel<CartUserResponseDto> entityModel = cartUserAssembler.toModel(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        Cart cart = service.get(id).orElseThrow();
        EntityModel<Cart> entityModel = assembler.toModel(cart);

        return ResponseEntity.ok(entityModel);
    }
}
