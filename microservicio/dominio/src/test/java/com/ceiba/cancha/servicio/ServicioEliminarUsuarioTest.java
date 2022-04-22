package com.ceiba.cancha.servicio;

import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarUsuarioTest {

    @Test
    @DisplayName("Deberia eliminar la Cancha llamando al repositorio")
    void deberiaEliminarLaCanchaLlamandoAlRepositorio() {
        RepositorioCancha repositorioCancha = Mockito.mock(RepositorioCancha.class);
        ServicioEliminarCancha servicioEliminarUsuario = new ServicioEliminarCancha(repositorioCancha);

        servicioEliminarUsuario.ejecutar(1l);

        Mockito.verify(repositorioCancha, Mockito.times(1)).eliminar(1l);

    }

}
