package com.ceiba.cancha.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.cancha.modelo.entidad.Cancha;
import com.ceiba.cancha.servicio.testdatabuilder.CanchaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CanchaTest {

    @Test
    @DisplayName("Deberia crear correctamente la cancha")
    void deberiaCrearCorrectamenteLaCancha() {

        Cancha cancha = new CanchaTestDataBuilder().conId(1L).build();

        assertEquals(1, cancha.getId());
        assertEquals("Cancha numero 1", cancha.getNombre());
        assertEquals(1, cancha.getIdTipoCancha());
    }

    @Test
    @DisplayName("Deberia fallar la creacion sin nombre de la cancha")
    void deberiaFallarSinNombreDeCancha() {

        CanchaTestDataBuilder canchaTestDataBuilder = new CanchaTestDataBuilder().conNombre(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    canchaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre de la cancha");
    }

    @Test
    @DisplayName("Deberia fallar la creacion sin tipo de cancha")
    void deberiaFallarSinTipoDeCancha() {

        CanchaTestDataBuilder canchaTestDataBuilder = new CanchaTestDataBuilder().conId(1L).conTipoCancha(null);
        BasePrueba.assertThrows(() -> {
                    canchaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el tipo de cancha");
    }

}