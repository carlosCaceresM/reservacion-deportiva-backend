package com.ceiba.reserva.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorActualizarReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCancelarReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCrearReserva;
import com.ceiba.reserva.comando.manejador.ManejadorEliminarReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Controlador comando Reserva"})
public class ComandoControladorReserva {

    private final ManejadorCrearReserva manejadorCrearReserva;
    private final ManejadorEliminarReserva manejadorEliminarReserva;
    private final ManejadorActualizarReserva manejadorActualizarReserva;
    private final ManejadorCancelarReserva manejadorCancelarReserva;

    @Autowired
    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva,
                                     ManejadorEliminarReserva manejadorEliminarReserva,
                                     ManejadorActualizarReserva manejadorActualizarReserva,
                                     ManejadorCancelarReserva manejadorCancelarReserva
    ) {
        this.manejadorCrearReserva = manejadorCrearReserva;
        this.manejadorEliminarReserva = manejadorEliminarReserva;
        this.manejadorActualizarReserva = manejadorActualizarReserva;
        this.manejadorCancelarReserva = manejadorCancelarReserva;
    }

    @PostMapping
    @ApiOperation("Crear Reserva")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return manejadorCrearReserva.ejecutar(comandoReserva);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Actualizar Reserva")
    public void actualizar(@RequestBody ComandoReserva comandoReserva, @PathVariable Long id) {
        comandoReserva.setId(id);
        manejadorActualizarReserva.ejecutar(comandoReserva);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation("Cancelar Reserva")
    public void cancelarReserva(@PathVariable Long id) {
        manejadorCancelarReserva.ejecutar(id);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar Reserva")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarReserva.ejecutar(id);
    }
}
