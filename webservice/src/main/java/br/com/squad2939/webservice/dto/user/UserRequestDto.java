package br.com.squad2939.webservice.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String postalCode;
}
