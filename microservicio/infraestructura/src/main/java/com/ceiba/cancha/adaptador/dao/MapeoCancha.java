package com.ceiba.cancha.adaptador.dao;

import com.ceiba.cancha.modelo.dto.DtoCancha;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCancha implements RowMapper<DtoCancha>, MapperResult {

    @Override
    public DtoCancha mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String tipoCancha = resultSet.getString("tipo_cancha");
        double tarifa = resultSet.getDouble("tarifa");

        return new DtoCancha(id, nombre, tipoCancha, tarifa);
    }

}
