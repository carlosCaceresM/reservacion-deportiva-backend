package com.ceiba.cancha.puerto.repositorio;

import com.ceiba.cancha.entidad.Cancha;

public interface RepositorioCancha {

    Long crear(Cancha cancha);

    void actualizar(Cancha cancha);

    void eliminar(Long id);

    boolean existe(String nombre);

    boolean existePorId(Long id);

}
