package br.com.squad2939.webservice.model;

import br.com.squad2939.webservice.dto.user.UserResponseDto;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Set;

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
    private User user;
    @OneToMany(mappedBy = "cart")
    Set<CartProduct> products;

    public UserResponseDto getUser() {
        ModelMapper mapper = new ModelMapper();

        if (user != null)
            return mapper.map(user, UserResponseDto.class);
        else
            return null;
    }
}
