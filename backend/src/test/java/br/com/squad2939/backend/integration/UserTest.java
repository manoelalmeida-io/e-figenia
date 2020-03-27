package br.com.squad2939.backend.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DataJpaTest
public class UserTest {

    @Test
    public void testSum() {
        assertEquals(2 + 2, 4);
    }
}
