package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorListarReservaPorId;
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
    private final ManejadorListarReservaPorId manejadorListarReservaPorId;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas,
                                      ManejadorListarReservaPorNombreUsuario manejadorListarReservaPorNombreUsuario, ManejadorListarReservaPorId manejadorListarReservaPorId) {
        this.manejadorListarReservas = manejadorListarReservas;
        this.manejadorListarReservaPorNombreUsuario = manejadorListarReservaPorNombreUsuario;
        this.manejadorListarReservaPorId = manejadorListarReservaPorId;
    }

    @GetMapping
    @ApiOperation("Listar Reservas")
    public List<DtoReserva> listar() {
        return this.manejadorListarReservas.ejecutar();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Listar Canchas por Tipo")
    public DtoReserva listarPorId(@PathVariable Long id) {
        return this.manejadorListarReservaPorId.ejecutar(id);
    }

    @GetMapping(value = "/nombre-usuario/{nombreUsuario}/cancha/{idCancha}")
    @ApiOperation("Listar Canchas por Tipo")
    public List<DtoReserva> listarPorNombreUsuario(@PathVariable String nombreUsuario, @PathVariable Long idCancha) {
        return this.manejadorListarReservaPorNombreUsuario.ejecutar(nombreUsuario, idCancha);
    }

}
