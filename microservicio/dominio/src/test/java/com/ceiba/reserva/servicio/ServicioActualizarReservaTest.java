package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

class ServicioActualizarReservaTest {

    @Test
    @DisplayName("Deberia validar la existencia previa de la Reserva")
    void deberiaValidarLaExistenciaPreviaDeLaReserva() {

        LocalDateTime fecha = LocalDateTime.now().withHour(17);

        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFecha(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        BasePrueba.assertThrows(
                () -> servicioActualizarReserva.ejecutar(reserva), ExcepcionDuplicidad.class,
                "La Reserva no exite en el sistema"
        );
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {

        LocalDateTime fecha = LocalDateTime.now().withHour(17);

        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFecha(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);

        servicioActualizarReserva.ejecutar(reserva);

        Mockito.verify(repositorioReserva, Mockito.times(1)).actualizar(reserva);
    }

}