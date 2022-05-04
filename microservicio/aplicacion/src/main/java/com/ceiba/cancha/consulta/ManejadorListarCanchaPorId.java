package com.ceiba.cancha.consulta;

import com.ceiba.cancha.puerto.dao.DaoCancha;
import org.springframework.stereotype.Component;

@Component
public class ManejadorListarCanchaPorId {

    private final DaoCancha daoCancha;

    public ManejadorListarCanchaPorId(DaoCancha daoCancha) {
        this.daoCancha = daoCancha;
    }

}
