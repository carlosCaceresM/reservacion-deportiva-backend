package com.ceiba.cancha.puerto.dao;

import com.ceiba.cancha.modelo.dto.DtoCancha;

import java.util.List;

public interface DaoCancha {

    List<DtoCancha> listarPorTipo(Long idTipoCancha);
}
