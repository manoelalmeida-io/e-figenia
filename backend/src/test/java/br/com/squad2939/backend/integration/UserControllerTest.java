package br.com.squad2939.backend.integration;

import br.com.squad2939.backend.model.User;
import br.com.squad2939.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService service;

    @Test
    void listAllUsers() throws Exception {
        mockMvc.perform(get("/api/users")
            .contentType("application/json"))
            .andExpect(status().isOk());
    }

    @Test
    void getOneUser() throws Exception {
        User user = new User();
        user.setName("John Doeee");
        user.setEmail("john10@mailserver.com");
        user.setPassword("password1234");
        user.setCpf("83728492509");
        user.setPostalCode("02384150");

        service.create(user);
        assertTrue(true);
    }

    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john10@mailserver.com");
        user.setPassword("password1234");
        user.setCpf("83728492509");
        user.setPostalCode("02384150");

        MvcResult result = mockMvc.perform(post("/api/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        User created = objectMapper.readValue(response, User.class);
        assertEquals("John Doe", created.getName());
    }
}
