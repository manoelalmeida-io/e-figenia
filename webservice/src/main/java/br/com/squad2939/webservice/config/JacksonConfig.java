package br.com.squad2939.webservice.config;

import br.com.squad2939.webservice.model.User;
import br.com.squad2939.webservice.repository.UserRepository;
import br.com.squad2939.webservice.serializer.UserDeserialize;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(userModuleMapper());

        return objectMapper;
    }

    public SimpleModule userModuleMapper() {
        SimpleModule userModule = new SimpleModule("JSONCartModule", PackageVersion.VERSION);
        userModule.addDeserializer(User.class, new UserDeserialize(repository));
        return userModule;
    }
}
