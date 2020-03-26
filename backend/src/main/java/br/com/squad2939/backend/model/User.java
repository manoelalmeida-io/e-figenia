package br.com.squad2939.backend.model;

import br.com.squad2939.backend.serialize.CartListSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String postalCode;

    private Boolean admin = false;

    @OneToMany(mappedBy = "user")
    @JsonSerialize(using = CartListSerializer.class)
    private List<Cart> carts;
}
