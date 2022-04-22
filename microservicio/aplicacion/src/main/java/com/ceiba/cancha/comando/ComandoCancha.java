package com.ceiba.cancha.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCancha {

    private Long id;
    private String nombre;
    private Long idTipoCancha;
}
