package br.com.squad2939.backend.unit;

import br.com.squad2939.backend.model.Product;
import br.com.squad2939.backend.repository.ProductRepository;
import br.com.squad2939.backend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

public class ProductServiceTest {

    private ProductRepository repository = Mockito.mock(ProductRepository.class);
    private ProductService service;

    @BeforeEach
    void init() {
        this.service = new ProductService(repository);
    }

    @Test
    void listAllProducts() {
        String message = "Should list all products from repository";

        List<Product> products = this.service.all();
        assertNotNull(products, message);
    }

    @Test
    void getOneProduct() {
        String message = "Should get a user from id";

        Optional<Product> product = this.service.one(1L);
        assertNotNull(product, message);
    }

    @Test
    void create() {
        String message = "Should create a product from Product object";

        Product newProduct = new Product();
        newProduct.setName("Resistor");
        newProduct.setPrice(0.15);
        newProduct.setQtyStock(50);

        Mockito.when(this.repository.save(any(Product.class))).then(returnsFirstArg());

        Product product = this.service.create(newProduct);
        assertNotNull(product, message);

        assertEquals("Resistor", product.getName());
        assertEquals(0.15, product.getPrice());
    }

    @Test
    void update() {
        String message = "Should update an existent product";

        Product existentProduct = new Product();
        existentProduct.setId(1L);
        existentProduct.setName("Resistor");
        existentProduct.setPrice(0.15);
        existentProduct.setQtyStock(50);

        Mockito.when(this.repository.save(any(Product.class))).then(returnsFirstArg());
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(existentProduct));

        Product updateProduct = new Product();
        updateProduct.setName("Resistor");
        updateProduct.setPrice(0.10);
        updateProduct.setQtyStock(25);

        Product product = this.service.update(1L, updateProduct);

        assertEquals(1L, product.getId(), message);
        assertEquals(0.10, product.getPrice(), message);
        assertEquals(25, product.getQtyStock(), message);
    }

    @Test
    void delete() {
        String message = "Should delete an existent product";

        Product existentProduct = new Product();
        existentProduct.setId(1L);
        existentProduct.setName("Resistor");
        existentProduct.setPrice(0.15);
        existentProduct.setQtyStock(50);

        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(existentProduct));
        assertDoesNotThrow(() -> this.service.delete(1L), message);
    }
}
