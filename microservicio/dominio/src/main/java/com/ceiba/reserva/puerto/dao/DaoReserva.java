package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.util.List;

public interface DaoReserva {

    List<DtoReserva> listarPorNombreUsuario(String nombreUsuario);

    DtoReserva listarPorId(Long id);

}