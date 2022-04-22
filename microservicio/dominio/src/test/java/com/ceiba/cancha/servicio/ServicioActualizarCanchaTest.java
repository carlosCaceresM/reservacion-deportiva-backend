package com.ceiba.cancha.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cancha.entidad.Cancha;
import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;
import com.ceiba.cancha.servicio.testdatabuilder.CanchaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarCanchaTest {


    @Test
    @DisplayName("Deberia validar la existencia previa de la cancha")
    void deberiaValidarLaExistenciaPreviaDeLaCancha() {

        Cancha cancha = new CanchaTestDataBuilder().conId(1L).build();
        RepositorioCancha repositorioCancha = Mockito.mock(RepositorioCancha.class);
        Mockito.when(repositorioCancha.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarCancha servicioActualizarCancha = new ServicioActualizarCancha(repositorioCancha);
        BasePrueba.assertThrows(() -> servicioActualizarCancha.ejecutar(cancha), ExcepcionDuplicidad.class, "La cancha no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {

        Cancha cancha = new CanchaTestDataBuilder().conId(1L).build();
        RepositorioCancha repositorioUsuario = Mockito.mock(RepositorioCancha.class);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarCancha servicioActualizarCancha = new ServicioActualizarCancha(repositorioUsuario);

        servicioActualizarCancha.ejecutar(cancha);

        Mockito.verify(repositorioUsuario, Mockito.times(1)).actualizar(cancha);
    }

}