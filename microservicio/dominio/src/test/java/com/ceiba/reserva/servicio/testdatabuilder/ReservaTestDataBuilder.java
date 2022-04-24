package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
    private String nombreUsuario;
    private LocalDateTime fecha;
    private int horasReservadas;
    private double valorPagar;
    private boolean estado;
    private Long idCancha;

    public ReservaTestDataBuilder() {
        nombreUsuario = "Hiko Seijuro";
        fecha = LocalDateTime.parse("2022-04-22T19:12:43");
        horasReservadas = 1;
        valorPagar = 60000;
        estado = true;
        idCancha = 1L;
    }

    public ReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ReservaTestDataBuilder conNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public ReservaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public ReservaTestDataBuilder conHorasReservadas(int horasReservadas) {
        this.horasReservadas = horasReservadas;
        return this;
    }

    public ReservaTestDataBuilder conValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
        return this;
    }

    public ReservaTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public ReservaTestDataBuilder conIdCancha(Long idCancha) {
        this.idCancha = idCancha;
        return this;
    }

    public Reserva build() {
        return new Reserva(id, nombreUsuario, fecha, horasReservadas, valorPagar, estado, idCancha);
    }
}
