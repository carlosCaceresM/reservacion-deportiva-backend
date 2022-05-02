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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearReservaTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia de la Reserva")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaReserva() {

        LocalDateTime fecha = LocalDateTime.parse("2022-05-22T19:12:43");

        Reserva reserva = new ReservaTestDataBuilder().conFecha(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe( fecha, 1L)).thenReturn(true);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);

        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva),
                ExcepcionDuplicidad.class, "La reserva ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear la Reserva de manera correcta")
    void deberiaCrearLaCanchaDeManeraCorrecta() {

        LocalDateTime fecha = LocalDateTime.parse("2022-05-22T19:12:43");

        Reserva reserva = new ReservaTestDataBuilder().conFecha(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe(fecha,1L)).thenReturn(false);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(10L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);

        Long idCancha = servicioCrearReserva.ejecutar(reserva);

        assertEquals(10L, idCancha);
        Mockito.verify(repositorioReserva, Mockito.times(1)).crear(reserva);
    }
}
