package com.ceiba.cancha.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Cancha {

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CANCHA = "Se debe ingresar el nombre de la cancha";
    private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_CANCHA = "Se debe ingresar el tipo de cancha";

    private Long id;
    private String nombre;
    private Long idTipoCancha;

    public Cancha(Long id, String nombre, Long idTipoCancha) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CANCHA);
        validarObligatorio(idTipoCancha, SE_DEBE_INGRESAR_EL_TIPO_DE_CANCHA);

        this.id = id;
        this.nombre = nombre;
        this.idTipoCancha = idTipoCancha;
    }
}