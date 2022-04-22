package com.ceiba.cancha.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cancha.comando.ComandoCancha;
import com.ceiba.cancha.comando.manejador.ManejadorActualizarCancha;
import com.ceiba.cancha.comando.manejador.ManejadorCrearCancha;
import com.ceiba.cancha.comando.manejador.ManejadorEliminarCancha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canchas")
@Api(tags = { "Controlador comando cancha"})
public class ComandoControladorCancha {

    private final ManejadorCrearCancha manejadorCrearCancha;
	private final ManejadorEliminarCancha manejadorEliminarCancha;
	private final ManejadorActualizarCancha manejadorActualizarCancha;

    @Autowired
    public ComandoControladorCancha(ManejadorCrearCancha manejadorCrearCancha,
									ManejadorEliminarCancha manejadorEliminarCancha,
									ManejadorActualizarCancha manejadorActualizarCancha) {
        this.manejadorCrearCancha = manejadorCrearCancha;
		this.manejadorEliminarCancha = manejadorEliminarCancha;
		this.manejadorActualizarCancha = manejadorActualizarCancha;
    }

    @PostMapping
    @ApiOperation("Crear Cancha")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCancha comandoCancha) {
        return manejadorCrearCancha.ejecutar(comandoCancha);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Cancha")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarCancha.ejecutar(id);
	}

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Cancha")
	public void actualizar(@RequestBody ComandoCancha comandoCancha,@PathVariable Long id) {
		comandoCancha.setId(id);
		manejadorActualizarCancha.ejecutar(comandoCancha);
	}
}
