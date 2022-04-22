package com.ceiba.cancha.adaptador.dao;

import com.ceiba.cancha.modelo.dto.DtoCancha;
import com.ceiba.cancha.puerto.dao.DaoCancha;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoCanchaMysql implements DaoCancha {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cancha", value = "listarPorTipo")
    private static String sqlListarPorTipo;

    public DaoCanchaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCancha> listarPorTipo(Long idTipoCancha) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idTipoCancha", idTipoCancha);
        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .query(sqlListarPorTipo, paramSource, new MapeoCancha());
    }
}
