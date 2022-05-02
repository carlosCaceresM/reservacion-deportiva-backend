package com.ceiba.reserva.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.reserva.servicio.ServicioCancelarReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCancelarReserva implements ManejadorComando<Long> {

    private final ServicioCancelarReserva servicioActualizarReserva;

    public ManejadorCancelarReserva(ServicioCancelarReserva servicioCancelarReserva
    ) {
        this.servicioActualizarReserva = servicioCancelarReserva;
    }

    public void ejecutar(Long id) {
        this.servicioActualizarReserva.ejecutar(id);
    }
}
