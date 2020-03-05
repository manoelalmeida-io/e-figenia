package br.com.squad2939.webservice.repository;

import br.com.squad2939.webservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
