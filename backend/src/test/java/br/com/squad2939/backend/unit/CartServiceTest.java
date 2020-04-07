package br.com.squad2939.backend.unit;

import br.com.squad2939.backend.model.Cart;
import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.repository.CartRepository;
import br.com.squad2939.backend.repository.UserRepository;
import br.com.squad2939.backend.service.CartService;
import br.com.squad2939.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

public class CartServiceTest {
    private CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private CartService cartService;

    @BeforeEach
    void init() {
        UserService userService = new UserService(this.userRepository);
        this.cartService = new CartService(this.cartRepository, userService);
    }

    @Test
    void listAllCarts() {
        String message = "Should list all carts from repository";

        List<Cart> carts = this.cartService.all();
        assertNotNull(carts, message);
    }

    @Test
    void getOneCart() {
        String message = "Should get a cart from id";

        Optional<Cart> cart = cartService.one(1L);
        assertNotNull(cart, message);
    }

    @Test
    void createCart() {
        String message = "Should create a new cart from Cart object";

        User cartUser = new User();
        cartUser.setId(1L);

        Cart newCart = new Cart();
        newCart.setUser(cartUser);
        newCart.setFinished(false);

        Mockito.when(this.userRepository.findById(1L)).thenReturn(Optional.of(cartUser));
        Mockito.when(this.cartRepository.save(any(Cart.class))).then(returnsFirstArg());

        Cart cart = cartService.create(newCart);

        assertNotNull(cart, message);
        assertEquals(1L, cart.getUser().getId(), message);
        assertEquals(false, cart.getFinished(), message);
    }

    @Test
    void updateCart() {
        String message = "Should update an existing cart";

        User cartUser = new User();
        cartUser.setId(1L);

        Cart existingCart = new Cart();
        existingCart.setId(1L);
        existingCart.setUser(cartUser);
        existingCart.setFinished(false);

        Mockito.when(this.cartRepository.findById(1L)).thenReturn(Optional.of(existingCart));
        Mockito.when(this.userRepository.findById(1L)).thenReturn(Optional.of(cartUser));
        Mockito.when(this.cartRepository.save(any(Cart.class))).then(returnsFirstArg());

        Cart updateCart = new Cart();
        updateCart.setUser(cartUser);
        updateCart.setFinished(true);

        Cart cart = cartService.update(1L, updateCart);

        assertNotNull(cart, message);
        assertEquals(1L, cart.getId(), message);
        assertEquals(cartUser, cart.getUser(), message);
        assertEquals(true, cart.getFinished(), message);
    }

    @Test
    void deleteCart() {
        String message = "Should delete an existing cart from id";

        User cartUser = new User();
        cartUser.setId(1L);

        Cart existingCart = new Cart();
        existingCart.setId(1L);
        existingCart.setUser(cartUser);
        existingCart.setFinished(false);

        Mockito.when(this.cartRepository.findById(1L)).thenReturn(Optional.of(existingCart));
        assertDoesNotThrow(() -> this.cartService.delete(1L), message);
    }
}
