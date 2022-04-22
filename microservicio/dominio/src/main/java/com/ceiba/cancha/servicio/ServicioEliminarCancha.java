package com.ceiba.cancha.servicio;

import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;

public class ServicioEliminarCancha {

    private final RepositorioCancha repositorioCancha;

    public ServicioEliminarCancha(RepositorioCancha repositorioCancha) {
        this.repositorioCancha = repositorioCancha;
    }

    public void ejecutar(Long id) {
        this.repositorioCancha.eliminar(id);
    }
}
