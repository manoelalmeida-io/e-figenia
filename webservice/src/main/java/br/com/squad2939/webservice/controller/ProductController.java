package br.com.squad2939.webservice.controller;

import br.com.squad2939.webservice.model.Product;
import br.com.squad2939.webservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("products")
    public ResponseEntity<?> all() {
        List<Product> products = service.all();
        return ResponseEntity.ok(products);
    }
}
