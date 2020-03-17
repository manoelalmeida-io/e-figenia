package br.com.squad2939.webservice.service;

import br.com.squad2939.webservice.model.Product;
import br.com.squad2939.webservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> all() {
        return repository.findAll();
    }
}
