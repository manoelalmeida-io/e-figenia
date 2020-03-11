package br.com.squad2939.webservice.serializer;

import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class UserDeserialize extends StdDeserializer<User> {

    private UserRepository repository;

    public UserDeserialize(UserRepository repository) {
        super(User.class);
        this.repository = repository;
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return repository.findById(jsonParser.getLongValue()).orElseThrow();
    }
}
