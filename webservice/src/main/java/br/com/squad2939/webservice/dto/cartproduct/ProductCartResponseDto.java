package br.com.squad2939.webservice.dto.cartproduct;

import br.com.squad2939.webservice.dto.cart.CartResponseDto;
import br.com.squad2939.webservice.model.Cart;
import br.com.squad2939.webservice.model.key.CartProductKey;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ProductCartResponseDto {
    CartProductKey id;
    int qty;
}
