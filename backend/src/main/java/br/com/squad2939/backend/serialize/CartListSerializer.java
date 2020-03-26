package br.com.squad2939.backend.serialize;

import br.com.squad2939.backend.model.Cart;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartListSerializer extends StdSerializer<List<Cart>> {

    public CartListSerializer() {
        this(null);
    }

    public CartListSerializer(Class<List<Cart>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Cart> carts, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        List<Long> ids = new ArrayList<>();
        for (Cart cart : carts) {
            ids.add(cart.getId());
        }

        jsonGenerator.writeObject(ids);
    }
}
