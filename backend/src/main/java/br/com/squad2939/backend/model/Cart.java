package br.com.squad2939.backend.model;

import br.com.squad2939.backend.serialize.UserDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean finished;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    @JsonDeserialize(using = UserDeserializer.class)
    private User user;
}
