package com.taxi.ws.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("SolicitudTransporte")
public class SolicitudTransporte {

    @Id
    private String id;
    private String id_empresa;
    private String id_empleado;
    private double[] coordenadas_origen;
    private double[] coordenadas_destino;
    private String direccion_origen;
    private String dirrecion_destino;
    private String kilometraje;
    private BigDecimal precio;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private LocalDate fecha_solicitud;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private LocalDate fecha_entrega;
    private String cod_estado;

}
