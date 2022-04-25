package com.ceiba.reserva.adaptador.dao;

import com.ceiba.cancha.adaptador.dao.MapeoCancha;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoReservaMysql implements DaoReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "reserva", value = "listar")
    private static String sqlListarReserva;

    @SqlStatement(namespace = "reserva", value = "listarPorId")
    private static String sqlListarReservaPorId;

    @SqlStatement(namespace = "reserva", value = "listarPorNombreUsuario")
    private static String sqlListarPorNombreUsuario;

    public DaoReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoReserva> listar() {
        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .query(sqlListarReserva, new MapeoReserva());
    }

    @Override
    public DtoReserva listarPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .queryForObject(sqlListarReservaPorId, paramSource,  new MapeoReserva());

    }

    @Override
    public DtoReserva listarPorNombreUsuario(String nombreUsuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombreUsuario", nombreUsuario);
        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .queryForObject(sqlListarPorNombreUsuario, paramSource, new MapeoReserva());
    }
}
