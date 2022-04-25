package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private String nombreUsuario;
    private LocalDateTime fecha;
    private int horasReservadas;
    private double valorPagar;
    private boolean estado;
    private Long idCancha;

    public ComandoReservaTestDataBuilder() {
        nombreUsuario = UUID.randomUUID().toString().substring(0, 20);
        fecha = LocalDateTime.parse("2022-04-26T17:12:43");
        horasReservadas = 2;
        valorPagar = 60000;
        estado = true;
        idCancha = 1L;

    }

    public ComandoReservaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public ComandoReservaTestDataBuilder conIdCancha(Long idCancha) {
        this.idCancha = idCancha;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, nombreUsuario, fecha, horasReservadas, horasReservadas, estado, idCancha);
    }
}
