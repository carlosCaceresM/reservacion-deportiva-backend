package com.ceiba.cancha.servicio.testdatabuilder;

import com.ceiba.cancha.comando.ComandoCancha;

import java.util.UUID;

public class ComandoCanchaTestDataBuilder {

    private Long id;
    private String nombre;
    private Long idTipoCancha;

    public ComandoCanchaTestDataBuilder() {
        nombre = UUID.randomUUID().toString().substring(0,20);
        idTipoCancha = 2L;
    }

    public ComandoCanchaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoCancha build() {
        return new ComandoCancha(id, nombre, idTipoCancha);
    }

}
