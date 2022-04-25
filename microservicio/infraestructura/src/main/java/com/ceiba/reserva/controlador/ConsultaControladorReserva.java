package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorListarReservaPorNombreUsuario;
import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Controlador consulta Reserva"})
public class ConsultaControladorReserva {

    private final ManejadorListarReservas manejadorListarReservas;
    private final ManejadorListarReservaPorNombreUsuario manejadorListarReservaPorNombreUsuario;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas,
                                      ManejadorListarReservaPorNombreUsuario manejadorListarReservaPorNombreUsuario) {
        this.manejadorListarReservas = manejadorListarReservas;
        this.manejadorListarReservaPorNombreUsuario = manejadorListarReservaPorNombreUsuario;
    }

    @GetMapping
    @ApiOperation("Listar Reservas")
    public List<DtoReserva> listar() {
        return this.manejadorListarReservas.ejecutar();
    }

    @GetMapping(value = "{nombreUsuario}")
    @ApiOperation("Listar Canchas por Tipo")
    public DtoReserva listarPorNombreUsuario(@PathVariable String nombreUsuario) {
        return this.manejadorListarReservaPorNombreUsuario.ejecutar(nombreUsuario);
    }

}
