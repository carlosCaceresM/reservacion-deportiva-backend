package com.ceiba.cancha.adaptador.dao;

import com.ceiba.cancha.modelo.dto.DtoCancha;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCancha implements RowMapper<DtoCancha>, MapperResult {

    @Override
    public DtoCancha mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        Long idTipoCancha = resultSet.getLong("id_tipo_cancha");

        return new DtoCancha(id, nombre, idTipoCancha);
    }

}
