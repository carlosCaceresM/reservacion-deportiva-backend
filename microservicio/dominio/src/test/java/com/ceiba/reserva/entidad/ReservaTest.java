package com.ceiba.reserva.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaTest {

    @Test
    @DisplayName("Deberia crear correctamente la Reserva")
    void deberiaCrearCorrectamenteLaReserva() {

        LocalDateTime fecha = LocalDateTime.parse("2022-05-22T19:12:43");
        double valorPagar = 60000;

        Reserva reserva = new ReservaTestDataBuilder()
                .conId(1L)
                .conFecha(fecha)
                .conValorPagar(valorPagar)
                .build();

        assertEquals(1, reserva.getId());
        assertEquals("Hiko Seijuro", reserva.getNombreUsuario());
        assertEquals(1, reserva.getIdCancha());
        assertEquals(fecha, reserva.getFecha());
        assertTrue(reserva.isEstado());
        assertEquals(1, reserva.getHorasReservadas());
        assertEquals(valorPagar, reserva.getValorPagar());

    }

    @Test
    @DisplayName("Deberia crear correctamente la Reserva con descuento")
    void deberiaCrearCorrectamenteLaReservaConDescuento() {

        LocalDateTime fecha = LocalDateTime.parse("2022-05-24T19:12:43");
        double valorConDescuento = 48000;

        Reserva reserva = new ReservaTestDataBuilder()
                .conId(1L)
                .conFecha(fecha)
                .build();

        assertEquals(valorConDescuento, reserva.getValorPagar());

    }

    @Test
    @DisplayName("Deberia fallar la creacion sin nombre de Usuario")
    void deberiaFallarSinNombreDeUsuario() {

        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombreUsuario(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre del Usuario");
    }

    @Test
    @DisplayName("Deberia fallar la creacion cuando la fecha es dia de descanso")
    void deberiaFallarCuandoLaFechaEsDiaDeDescanso() {

        LocalDateTime fecha = LocalDateTime.parse("2022-05-23T02:12:43");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conId(1L).conFecha(fecha);
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La fecha no puede ser igual al dia de descanso");
    }

    @Test
    @DisplayName("Deberia fallar la creacion cuando la hora no es habil")
    void deberiaFallarCuandoLaHoraNoEsHabil() {

        LocalDateTime fecha = LocalDateTime.parse("2022-05-22T02:12:43");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conId(1L).conFecha(fecha);
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La hora debe ser una hora habil de servicio");
    }

    @Test
    @DisplayName("Deberia fallar la creacion cuando la fecha es menor a la fecha actual")
    void deberiaFallarCuandoLafechaEsMenosAlDiaActual() {

        LocalDateTime fecha = LocalDateTime.parse("2022-04-26T02:12:43");
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conId(1L).conFecha(fecha);
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La fecha no puede ser menor al dia de actual");
    }
}