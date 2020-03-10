package br.com.squad2939.webservice.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponseDto {
    private String name;
    private String email;
    private String cpf;
    private String postalCode;
}
