package com.taxi.ws.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Empresa")
public class Empresa {

    @Id
    private String id;
    private String razon_social;
    private String ruc;
    private String direccion;
    private String estado;

}
