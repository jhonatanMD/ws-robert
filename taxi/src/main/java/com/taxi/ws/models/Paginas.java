package com.taxi.ws.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Paginas")
public class Paginas {

    @Id
    private String id;
    private String pagina;
    private String modulo;

}
