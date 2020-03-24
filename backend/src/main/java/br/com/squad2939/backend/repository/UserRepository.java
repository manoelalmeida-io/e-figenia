package br.com.squad2939.backend.repository;

import br.com.squad2939.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
