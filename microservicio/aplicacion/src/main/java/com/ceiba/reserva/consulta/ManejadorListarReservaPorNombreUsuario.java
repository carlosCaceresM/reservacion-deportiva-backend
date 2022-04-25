package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarReservaPorNombreUsuario {

    private final DaoReserva daoReserva;

    public ManejadorListarReservaPorNombreUsuario(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public DtoReserva ejecutar(String nombreUsuario) {
        return this.daoReserva.listarPorNombreUsuario(nombreUsuario);
    }
}