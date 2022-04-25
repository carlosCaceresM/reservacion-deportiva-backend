package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorListarReservaPorNombreUsuario;
import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping(value = "{nombreUsuario}/{idCancha}")
    @ApiOperation("Listar Canchas por Tipo")
    public List<DtoReserva> listarPorNombreUsuario(@PathVariable String nombreUsuario,@PathVariable Long idCancha) {
        return this.manejadorListarReservaPorNombreUsuario.ejecutar(nombreUsuario, idCancha);
    }

}
