package com.ceiba.cancha.controlador;

import com.ceiba.cancha.consulta.ManejadorListarCanchaPorTipo;
import com.ceiba.cancha.modelo.dto.DtoCancha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/canchas")
@Api(tags = {"Controlador consulta cancha"})
public class ConsultaControladorCancha {

    private final ManejadorListarCanchaPorTipo manejadorListarCanchaPorTipo;

    public ConsultaControladorCancha(ManejadorListarCanchaPorTipo manejadorListarCanchaPorTipo) {
        this.manejadorListarCanchaPorTipo = manejadorListarCanchaPorTipo;
    }

    @GetMapping(value = "/{idTipoCancha}")
    @ApiOperation("Listar Canchas por Tipo")
    public List<DtoCancha> listar(@PathVariable Long idTipoCancha) {
        return this.manejadorListarCanchaPorTipo.ejecutar(idTipoCancha);
    }

}
