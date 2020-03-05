package br.com.squad2939.webservice.dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String email;
    private String cpf;
    private String postalCode;
}
