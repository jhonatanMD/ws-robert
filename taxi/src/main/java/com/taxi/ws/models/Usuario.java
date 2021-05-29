package com.taxi.ws.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("Usuario")
public class Usuario {


    @Id
    private String id;
    private String id_empleado;
    private String usuario;
    private String password;
    private int estado;
}
