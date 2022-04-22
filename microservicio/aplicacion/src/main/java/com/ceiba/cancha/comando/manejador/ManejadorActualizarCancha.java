package com.ceiba.cancha.comando.manejador;

import com.ceiba.cancha.comando.ComandoCancha;
import com.ceiba.cancha.comando.fabrica.FabricaCancha;
import com.ceiba.cancha.modelo.entidad.Cancha;
import com.ceiba.cancha.servicio.ServicioActualizarCancha;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarCancha implements ManejadorComando<ComandoCancha> {

    private final FabricaCancha fabricaCancha;
    private final ServicioActualizarCancha servicioActualizarCancha;

    public ManejadorActualizarCancha(FabricaCancha fabricaCancha, ServicioActualizarCancha servicioActualizarCancha) {
        this.fabricaCancha = fabricaCancha;
        this.servicioActualizarCancha = servicioActualizarCancha;
    }

    public void ejecutar(ComandoCancha comandoCancha) {
        Cancha cancha = this.fabricaCancha.crear(comandoCancha);
        this.servicioActualizarCancha.ejecutar(cancha);
    }
}
