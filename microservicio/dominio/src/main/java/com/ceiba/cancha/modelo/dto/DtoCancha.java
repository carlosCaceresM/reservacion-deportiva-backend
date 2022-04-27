package com.ceiba.cancha.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCancha {

    private Long id;
    private String nombre;
    private String tipoCancha;
    private double tafira;
}