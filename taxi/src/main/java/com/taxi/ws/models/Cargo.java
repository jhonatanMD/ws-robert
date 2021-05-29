package com.taxi.ws.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("Cargo")
public class Cargo {

    @Id
    private String id;
    private String cargo;
    private String id_empresa;
    private int estado;

}
