package com.taxi.ws.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Rol")
public class Rol {

    @Id
    private String id;
    private String id_empresa;
    private String rol;
    private List<String> paginas;

}
