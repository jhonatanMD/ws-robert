package com.taxi.ws.models.dto;

import com.taxi.ws.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatosDeUsuario {

    private Usuario usuario;
    private Empleado empleado;
    private Empresa empresa;
    private Rol rol;
    private List<Paginas> paginas;

}
