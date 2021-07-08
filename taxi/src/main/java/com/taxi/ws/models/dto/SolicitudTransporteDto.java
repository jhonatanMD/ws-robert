package com.taxi.ws.models.dto;


import com.taxi.ws.models.Empleado;
import com.taxi.ws.models.SolicitudTransporte;

import lombok.*;

@Getter
@Setter
@Builder
public class SolicitudTransporteDto{
    
    private SolicitudTransporte taxi;
    private Empleado empleado;
}