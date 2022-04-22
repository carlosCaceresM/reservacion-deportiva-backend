package com.ceiba.cancha.servicio.testdatabuilder;

import com.ceiba.cancha.entidad.Cancha;

public class CanchaTestDataBuilder {

    private Long id;
    private String nombre;
    private Long idTipoCancha;

    public CanchaTestDataBuilder() {
        nombre = "Cancha numero 1";
        idTipoCancha = 1L;

    }

    public CanchaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public CanchaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }


    public CanchaTestDataBuilder conTipoCancha(Long idTipoCancha) {
        this.idTipoCancha = idTipoCancha;
        return this;
    }


    public Cancha build() {
        return new Cancha(id, nombre, idTipoCancha);
    }
}
