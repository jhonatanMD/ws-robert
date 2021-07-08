package com.taxi.ws.models.dto;

import com.taxi.ws.models.Cargo;
import com.taxi.ws.models.Empleado;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosDeEmpleado {

    private Empleado empleado;
    private Cargo cargo;
}
