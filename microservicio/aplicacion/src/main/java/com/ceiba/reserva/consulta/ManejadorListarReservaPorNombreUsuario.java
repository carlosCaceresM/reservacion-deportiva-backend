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

    public List<DtoReserva> ejecutar(String nombreUsuario, Long idCancha) {
        return this.daoReserva.listarPorNombreUsuario(nombreUsuario, idCancha);
    }
}
