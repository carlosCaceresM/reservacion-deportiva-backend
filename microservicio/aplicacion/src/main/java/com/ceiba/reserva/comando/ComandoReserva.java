package com.ceiba.reserva.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva {

    private Long id;
    private String nombreUsuario;
    private LocalDateTime fecha;
    private int horasReservadas;
    private double valorPagar;
    private boolean estado;
    private Long idCancha;
}
