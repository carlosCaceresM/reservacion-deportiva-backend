package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioActualizarReserva {

    private static final String LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA = "La Reserva no exite en el sistema";

    private final RepositorioReserva repositorioCancha;

    public ServicioActualizarReserva(RepositorioReserva repositorioUsuario) {
        this.repositorioCancha = repositorioUsuario;
    }

    public void ejecutar(Reserva cancha) {
        validarExistenciaPrevia(cancha);
        this.repositorioCancha.actualizar(cancha);
    }

    private void validarExistenciaPrevia(Reserva cancha) {
        boolean existe = this.repositorioCancha.existePorId(cancha.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
