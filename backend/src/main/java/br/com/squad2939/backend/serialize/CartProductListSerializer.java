package br.com.squad2939.backend.serialize;

import br.com.squad2939.backend.model.CartProduct;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartProductListSerializer extends StdSerializer<List<CartProduct>> {
    public CartProductListSerializer() {
        this(null);
    }

    public CartProductListSerializer(Class<List<CartProduct>> t) {
        super(t);
    }

    @Override
    public void serialize(List<CartProduct> cartProducts, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        List<Long> ids = new ArrayList<>();
        for (CartProduct product : cartProducts) {
            ids.add(product.getId().getProductId());
        }

        jsonGenerator.writeObject(ids);
    }
}
