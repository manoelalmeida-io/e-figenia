package br.com.squad2939.webservice.service;

import br.com.squad2939.webservice.model.Cart;
import br.com.squad2939.webservice.model.CartProduct;
import br.com.squad2939.webservice.model.Product;
import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.model.key.CartProductKey;
import br.com.squad2939.webservice.repository.CartProductRepository;
import br.com.squad2939.webservice.repository.CartRepository;
import br.com.squad2939.webservice.repository.ProductRepository;
import br.com.squad2939.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    public Cart toCart(Long productId) {
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            Optional<Cart> cart = Optional.ofNullable(cartRepository.findByUserIdAndFinished(userId, false));

            if (cart.isPresent()) {
                cartProductRepository.save(createCartProduct(cart.get(), product.get()));
            }
            else {
                Optional<User> user = userRepository.findById(userId);

                if (user.isPresent()) {
                    Cart newCart = new Cart();
                    newCart.setUser(user.get());
                    newCart.setFinished(false);
                    cartRepository.save(newCart);

                    cartProductRepository.save(createCartProduct(newCart, product.get()));
                }
            }
        }

        return null;
    }

    private CartProduct createCartProduct(Cart cart, Product product) {
        CartProductKey cartProductKey = new CartProductKey();
        cartProductKey.setCartId(cart.getId());
        cartProductKey.setProductId(product.getId());

        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(cartProductKey);
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);

        return cartProduct;
    }
}
