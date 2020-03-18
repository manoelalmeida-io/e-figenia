package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.assembler.ProductResourceAssembler;
import br.com.squad2939.webservice.dto.ErrorDto;
import br.com.squad2939.webservice.model.Cart;
import br.com.squad2939.webservice.model.Product;
import br.com.squad2939.webservice.service.ProductCartService;
import br.com.squad2939.webservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductCartService productCartService;
    @Autowired
    private ProductResourceAssembler assembler;

    @GetMapping("products")
    public ResponseEntity<?> all() {
        List<Product> products = service.all();
        List<EntityModel<Product>> productList = products.stream()
                .map(product -> assembler.toModel(product))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productList);
    }

    @PostMapping("products")
    public ResponseEntity<?> create(@RequestBody Product newProduct) {
        Product product = service.create(newProduct);
        EntityModel<Product> entityModel = assembler.toModel(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        Optional<Product> product = service.one(id);

        if (product.isPresent()) {
            EntityModel<Product> entityModel = assembler.toModel(product.get());
            return ResponseEntity.ok(entityModel);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Not found"));
    }

    @PostMapping("products/{id}/to-cart")
    public ResponseEntity<?> toCart(@PathVariable Long id) {
        Cart cart = productCartService.toCart(id);
        return ResponseEntity.ok(cart);
    }
}
