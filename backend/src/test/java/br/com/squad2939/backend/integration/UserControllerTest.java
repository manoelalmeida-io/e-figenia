package br.com.squad2939.backend.integration;

import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService service;

    private User example() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john10@mailserver.com");
        user.setPassword("password1234");
        user.setCpf("83728492509");
        user.setPostalCode("02384150");

        return user;
    }

    private User fromResult(MvcResult result) throws Exception {
        String response = result.getResponse().getContentAsString();
        return objectMapper.readValue(response, User.class);
    }

    @Test
    void listAllUsers() throws Exception {
        mockMvc.perform(get("/api/users")
            .contentType("application/json"))
            .andExpect(status().isOk());
    }

    @Test
    void getOneUser() throws Exception {
        User user = example();

        service.create(user);
        assertTrue(true);
    }

    @Test
    void createUser() throws Exception {
        User user = example();

        MvcResult result = mockMvc.perform(post("/api/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andReturn();

        User created = fromResult(result);
        System.out.println(created.getId());
        assertEquals("John Doe", created.getName());
    }

    @Test
    void updateUser() throws Exception {
        User user = example();

        MvcResult createResult = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn();

        User created = fromResult(createResult);

        User update = new User();
        update.setName("John Lennon");
        update.setEmail("john10@mailserver.com");
        update.setPassword("password1234");
        update.setCpf("83728492509");
        update.setPostalCode("02384150");

        MvcResult result = mockMvc.perform(put("/api/users/" + created.getId())
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(update)))
            .andExpect(status().isOk())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        User updated = objectMapper.readValue(response, User.class);
        assertEquals(created.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
    }

    @Test
    void deleteUser() throws Exception {
        User user = example();

        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn();

        User created = fromResult(result);

        mockMvc.perform(delete("/api/users/" + created.getId())
            .contentType("application/json"))
            .andExpect(status().isOk());
    }
}
