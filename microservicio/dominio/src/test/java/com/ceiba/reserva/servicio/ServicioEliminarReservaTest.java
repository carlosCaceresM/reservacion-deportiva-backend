package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class ServicioEliminarReservaTest {

    @Test
    @DisplayName("Deberia eliminar la Reserva llamando al repositorio")
    void deberiaEliminarLaReservaLlamandoAlRepositorio() {
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva);

        servicioEliminarReserva.ejecutar(1l);

        Mockito.verify(repositorioReserva, Mockito.times(1)).eliminar(1l);

    }
}
