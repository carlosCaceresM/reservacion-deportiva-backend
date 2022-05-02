package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

class ServicioCancelarReservaTest {

    @Test
    @DisplayName("Deberia Cancelar la Reservacion llamando al repositorio")
    void deberiaCancelarLaReservacionLlamandoAlRepositorio() {

        LocalDateTime fechaReservacion = LocalDateTime.now().plusDays(1);
        DtoReserva dtoReserva = new DtoReserva(1L, "hiko",
                fechaReservacion, 2, 600000,
                true, "campo 1"
        );

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.doNothing().when(repositorioReserva).cancelarReserva(1L);
        Mockito.when(daoReserva.listarPorId(Mockito.anyLong())).thenReturn(dtoReserva);
        ServicioCancelarReserva servicioCancelarReserva = new ServicioCancelarReserva(repositorioReserva, daoReserva);

        servicioCancelarReserva.ejecutar(1L);

        Mockito.verify(repositorioReserva, Mockito.times(1)).cancelarReserva(1L);

    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valida la fecha de cancelacion")
    void deberiaLanzarUnaExepcionCuandoSeValidaLaFechaDeCancelacion() {

        LocalDateTime fechaReservacion = LocalDateTime.now().plusHours(-1);
        DtoReserva dtoReserva = new DtoReserva(1L, "hiko",
                fechaReservacion, 2, 600000,
                true, "campo 1"
        );

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.doNothing().when(repositorioReserva).cancelarReserva(1L);
        Mockito.when(daoReserva.listarPorId(Mockito.anyLong())).thenReturn(dtoReserva);
        ServicioCancelarReserva servicioCancelarReserva = new ServicioCancelarReserva(repositorioReserva, daoReserva);

        BasePrueba.assertThrows(() -> servicioCancelarReserva.ejecutar(1L),
                ExcepcionValorInvalido.class, "Lo setimos, la Reserva solo puede ser cancelada 3 horas antes");
    }

}