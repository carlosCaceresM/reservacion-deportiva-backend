package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReserva {

    private Long id;
    private String nombreUsuario;
    private LocalDateTime fecha;
    private int horasReservadas;
    private double valorPagar;
    private boolean estado;
    private String cancha;
}