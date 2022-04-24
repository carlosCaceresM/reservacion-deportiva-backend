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

public class ServicioEliminarReservaTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valida la fecha de cancelacion")
    void deberiaLanzarUnaExepcionCuandoSeValidaLaFechaDeCancelacion() {

        LocalDateTime fechaReservacion = LocalDateTime.now().plusHours(4);
        DtoReserva dtoReserva = new DtoReserva(1L, "hiko",
                fechaReservacion, 2, 600000,
                true, "campo 1"
        );

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.doNothing().when(repositorioReserva).eliminar(1L);
        Mockito.when(daoReserva.listarPorId(Mockito.anyLong())).thenReturn(dtoReserva);
        ServicioEliminarReserva servicioEliminarUsuario = new ServicioEliminarReserva(repositorioReserva, daoReserva);

        servicioEliminarUsuario.ejecutar(1L);

        Mockito.verify(repositorioReserva, Mockito.times(1)).eliminar(1L);

    }

    @Test
    @DisplayName("Deberia eliminar la Reservacion llamando al repositorio")
    void deberiaEliminarLaReservacionLlamandoAlRepositorio() {

        DtoReserva dtoReserva = new DtoReserva(1L, "hiko",
                LocalDateTime.now(), 2, 600000,
                true, "campo 1"
        );

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.doNothing().when(repositorioReserva).eliminar(1L);
        Mockito.when(daoReserva.listarPorId(Mockito.anyLong())).thenReturn(dtoReserva);
        ServicioEliminarReserva servicioEliminarUsuario = new ServicioEliminarReserva(repositorioReserva, daoReserva);

        BasePrueba.assertThrows(() -> servicioEliminarUsuario.ejecutar(1L),
                ExcepcionValorInvalido.class, "La reserva no puede ser eliminada");
    }

}
