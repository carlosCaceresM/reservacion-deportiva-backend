package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.time.LocalDateTime;
import java.util.List;

public interface DaoReserva {

    List<DtoReserva> listar();

    DtoReserva listarPorId(Long id);

    List<DtoReserva> listarPorNombreUsuario(String nombreUsuario, Long idCancha);

}