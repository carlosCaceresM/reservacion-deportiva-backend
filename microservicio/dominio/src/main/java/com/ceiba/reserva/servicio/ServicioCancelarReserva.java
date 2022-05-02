package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;

public class ServicioCancelarReserva {

    private static final String LA_RESERVA_NO_PUEDE_SER_CANCELADA = "Lo setimos, la Reserva solo puede ser cancelada 3 horas antes";

    private final RepositorioReserva repositorioCancha;
    private final DaoReserva daoReserva;

    public ServicioCancelarReserva(RepositorioReserva repositorioUsuario, DaoReserva daoReserva) {
        this.repositorioCancha = repositorioUsuario;
        this.daoReserva = daoReserva;
    }

    public void ejecutar(Long id) {
        validarHoraPermitidaParaAnularReserva(id);
        this.repositorioCancha.cancelarReserva(id);
    }


    private void validarHoraPermitidaParaAnularReserva(Long id) {
        DtoReserva dtoReserva = this.daoReserva.listarPorId(id);
        if (LocalDateTime.now().isAfter(dtoReserva.getFecha().plusHours(-3))) {
            throw new ExcepcionValorInvalido(LA_RESERVA_NO_PUEDE_SER_CANCELADA);
        }
    }
}
