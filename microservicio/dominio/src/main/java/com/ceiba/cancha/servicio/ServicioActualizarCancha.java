package com.ceiba.cancha.servicio;

import com.ceiba.cancha.modelo.entidad.Cancha;
import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioActualizarCancha {

    private static final String LA_CANCHA_NO_EXISTE_EN_EL_SISTEMA = "La cancha no existe en el sistema";

    private final RepositorioCancha repositorioCancha;

    public ServicioActualizarCancha(RepositorioCancha repositorioUsuario) {
        this.repositorioCancha = repositorioUsuario;
    }

    public void ejecutar(Cancha cancha) {
        validarExistenciaPrevia(cancha);
        this.repositorioCancha.actualizar(cancha);
    }

    private void validarExistenciaPrevia(Cancha cancha) {
        boolean existe = this.repositorioCancha.existePorId(cancha.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(LA_CANCHA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
