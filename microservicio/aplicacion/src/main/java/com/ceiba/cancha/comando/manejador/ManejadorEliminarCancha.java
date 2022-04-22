package com.ceiba.cancha.comando.manejador;

import com.ceiba.cancha.servicio.ServicioEliminarCancha;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarCancha implements ManejadorComando<Long> {

    private final ServicioEliminarCancha servicioEliminarCancha;

    public ManejadorEliminarCancha(ServicioEliminarCancha servicioEliminarCancha) {
        this.servicioEliminarCancha = servicioEliminarCancha;
    }

    public void ejecutar(Long idCancha) {
        this.servicioEliminarCancha.ejecutar(idCancha);
    }
}
