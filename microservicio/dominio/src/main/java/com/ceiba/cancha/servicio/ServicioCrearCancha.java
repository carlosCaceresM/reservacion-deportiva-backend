package com.ceiba.cancha.servicio;

import com.ceiba.cancha.entidad.Cancha;
import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;


public class ServicioCrearCancha {

    private static final String LA_CANCHA_YA_EXISTE_EN_EL_SISTEMA = "La cancha ya existe en el sistema";

    private final RepositorioCancha repositorioCancha;

    public ServicioCrearCancha(RepositorioCancha repositorioCancha) {
        this.repositorioCancha = repositorioCancha;
    }

    public Long ejecutar(Cancha cancha) {
        validarExistenciaPrevia(cancha);
        return this.repositorioCancha.crear(cancha);
    }

    private void validarExistenciaPrevia(Cancha cancha) {
        boolean existe = this.repositorioCancha.existe(cancha.getNombre());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_CANCHA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
