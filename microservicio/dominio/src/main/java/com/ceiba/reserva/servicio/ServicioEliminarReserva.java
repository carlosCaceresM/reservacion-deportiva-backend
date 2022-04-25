package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;

public class ServicioEliminarReserva {

    private static final String LA_RESERVA_NO_PUEDE_SER_ANULADA = "La reserva no puede ser eliminada";

    private final RepositorioReserva repositorioReserva;
    private  final DaoReserva daoReserva;

    public ServicioEliminarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
    }

    public void ejecutar(Long id) {
        validarHoraPermitidaParaAnularReserva(id);
        this.repositorioReserva.eliminar(id);
    }

    private void validarHoraPermitidaParaAnularReserva(Long id) {
        DtoReserva dtoReserva = this.daoReserva.listarPorId(id);
        if (LocalDateTime.now().isAfter(dtoReserva.getFecha().plusHours(-3))) {
            throw new ExcepcionValorInvalido(LA_RESERVA_NO_PUEDE_SER_ANULADA);
        }
    }
}
