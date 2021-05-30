package com.taxi.ws.models.dto;

import com.taxi.ws.models.Empleado;
import com.taxi.ws.models.Empresa;
import com.taxi.ws.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatosDeUsuario {

    private Usuario usuario;
    private Empleado empleado;
    private Empresa empresa;

}
