package com.ceiba.cancha.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cancha.modelo.entidad.Cancha;
import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;
import com.ceiba.cancha.servicio.testdatabuilder.CanchaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearCanchaTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia de la  Cancha")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaCancha() {

        Cancha cancha = new CanchaTestDataBuilder().build();
        RepositorioCancha repositorioCancha = Mockito.mock(RepositorioCancha.class);
        Mockito.when(repositorioCancha.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearCancha servicioCrearCancha = new ServicioCrearCancha(repositorioCancha);

        BasePrueba.assertThrows(() -> servicioCrearCancha.ejecutar(cancha), ExcepcionDuplicidad.class,"La cancha ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear la Cancha de manera correcta")
    void deberiaCrearLaCanchaDeManeraCorrecta() {

        Cancha cancha = new CanchaTestDataBuilder().build();
        RepositorioCancha repositorioCancha = Mockito.mock(RepositorioCancha.class);
        Mockito.when(repositorioCancha.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioCancha.crear(cancha)).thenReturn(10L);
        ServicioCrearCancha servicioCrearCancha = new ServicioCrearCancha(repositorioCancha);

        Long idCancha = servicioCrearCancha.ejecutar(cancha);

        assertEquals(10L,idCancha);
        Mockito.verify(repositorioCancha, Mockito.times(1)).crear(cancha);
    }
}
