package com.ceiba.cancha.comando.fabrica;

import com.ceiba.cancha.comando.ComandoCancha;
import com.ceiba.cancha.modelo.entidad.Cancha;
import org.springframework.stereotype.Component;

@Component
public class FabricaCancha {

    public Cancha crear(ComandoCancha comandoCancha) {
        return new Cancha(comandoCancha.getId(),
                comandoCancha.getNombre(),
                comandoCancha.getIdTipoCancha()
        );
    }

}
