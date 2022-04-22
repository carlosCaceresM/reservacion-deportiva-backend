package com.ceiba.cancha.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cancha.comando.ComandoCancha;
import com.ceiba.cancha.comando.fabrica.FabricaCancha;
import com.ceiba.cancha.modelo.entidad.Cancha;
import com.ceiba.cancha.servicio.ServicioCrearCancha;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearCancha implements ManejadorComandoRespuesta<ComandoCancha, ComandoRespuesta<Long>> {

    private final FabricaCancha fabricaCancha;
    private final ServicioCrearCancha servicioCrearCancha;

    public ManejadorCrearCancha(FabricaCancha fabricaCancha, ServicioCrearCancha servicioCrearCancha) {
        this.fabricaCancha = fabricaCancha;
        this.servicioCrearCancha = servicioCrearCancha;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoCancha comandoUsuario) {
        Cancha cancha = this.fabricaCancha.crear(comandoUsuario);
        return new ComandoRespuesta<>(this.servicioCrearCancha.ejecutar(cancha));
    }
}
