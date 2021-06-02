package com.taxi.ws.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Constantes {

    public static final Map<String,String> ESTADO_TRANSPORTE = new HashMap<>(){{put("0","Pendiente");put("1","Tomado");
        put("2","Entregado");put("3","Rechazado");put("4","Cancelado");}};

    public static final double PRECIO_POR_METRO = 0.01;
}
