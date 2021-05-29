package com.taxi.ws.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Empleado")
public class Empleado {

    @Id
    private String id;
    private String nombre;
    private String ape_pat;
    private String ape_mat;
    private int edad;
    private String dni;
    private String email;
    private String id_empresa;
    private String id_cargo;
    private int estado;
}
