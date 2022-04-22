package com.ceiba.cancha.consulta;

import com.ceiba.cancha.modelo.dto.DtoCancha;
import com.ceiba.cancha.puerto.dao.DaoCancha;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarCanchaPorTipo {

    private final DaoCancha daoCancha;

    public ManejadorListarCanchaPorTipo(DaoCancha daoCancha) {
        this.daoCancha = daoCancha;
    }

    public List<DtoCancha> ejecutar(Long idTipoCancha) {
        return this.daoCancha.listarPorTipo(idTipoCancha);
    }
}
