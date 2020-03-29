package br.com.squad2939.backend.unit;

import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.repository.UserRepository;
import br.com.squad2939.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService;

    @BeforeEach
    void init() {
        this.userService = new UserService(userRepository);
    }

    @Test
    void listAllUsers() {
        String message = "Should list all users from repository";

        List<User> users = this.userService.all();
        assertNotNull(users, message);
    }

    @Test
    void getOneUser() {
        String message = "Should get a user from id";

        Optional<User> user = this.userService.one(1L);
        assertNotNull(user, message);
    }

    @Test
    void createUser() {
        String message = "Should create a user from User object";

        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@servidor.com");
        newUser.setPassword("password1234");
        newUser.setCpf("83728492509");
        newUser.setPostalCode("02384150");

        Mockito.when(this.userRepository.save(any(User.class))).then(returnsFirstArg());

        User user = this.userService.create(newUser);
        assertNotNull(user, message);

        assertEquals("John Doe", user.getName(), message);
        assertEquals("john.doe@servidor.com", user.getEmail(), message);
    }

    @Test
    void updateUser() {
        String message = "Should update an existent user";

        User existentUser = new User();
        existentUser.setId(1L);
        existentUser.setName("John Doe");
        existentUser.setEmail("john.doe@mailserver.com");
        existentUser.setPassword("password1234");
        existentUser.setCpf("83728492509");
        existentUser.setPostalCode("02384150");

        Mockito.when(this.userRepository.save(any(User.class))).then(returnsFirstArg());
        Mockito.when(this.userRepository.findById(1L)).thenReturn(Optional.of(existentUser));

        User updateUser = new User();
        updateUser.setName("John Doe");
        updateUser.setEmail("john10@mailserver.com");
        updateUser.setPassword("password1234");
        updateUser.setCpf("83728492509");
        updateUser.setPostalCode("02384150");

        User user = userService.update(1L, updateUser);

        assertEquals(1L, user.getId(), message);
        assertEquals("john10@mailserver.com", user.getEmail(), message);
        assertEquals("John Doe", user.getName(), message);
    }

    @Test
    void deleteUser() {
        String message = "Should delete an existing user";

        User existentUser = new User();
        existentUser.setId(1L);
        existentUser.setName("John Doe");
        existentUser.setEmail("john.doe@mailserver.com");
        existentUser.setPassword("password1234");
        existentUser.setCpf("83728492509");
        existentUser.setPostalCode("02384150");

        Mockito.when(this.userRepository.findById(1L)).thenReturn(Optional.of(existentUser));
        assertDoesNotThrow(() -> userService.delete(1L), message);
    }
}
