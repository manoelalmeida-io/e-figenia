package br.com.squad2939.backend.serialize;

import br.com.squad2939.backend.model.CartProduct;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCartListSerializer extends StdSerializer<List<CartProduct>> {
    public ProductCartListSerializer() {
        this(null);
    }

    public ProductCartListSerializer(Class<List<CartProduct>> t) {
        super(t);
    }

    @Override
    public void serialize(List<CartProduct> cartProductList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        List<Long> ids = new ArrayList<>();
        for (CartProduct carts : cartProductList) {
            ids.add(carts.getId().getCartId());
        }

        jsonGenerator.writeObject(ids);
    }
}
