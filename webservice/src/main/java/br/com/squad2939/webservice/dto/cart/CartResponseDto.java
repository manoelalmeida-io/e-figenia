package br.com.squad2939.webservice.dto.cart;

import br.com.squad2939.webservice.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartResponseDto {
    private Long id;
    private Boolean finished;
}
