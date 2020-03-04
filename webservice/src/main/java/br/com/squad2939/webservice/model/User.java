package br.com.squad2939.webservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
@Entity
@Table(name = "tb_user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Size(min = 10, max = 10)
    private String cpf;

    @Size(min = 8, max = 8)
    private String postalCode;
}
