package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "reserva", value = "crear")
    private static String sqlCrearReserva;

    @SqlStatement(namespace = "reserva", value = "actualizar")
    private static String sqlActualizarReserva;

    @SqlStatement(namespace = "reserva", value = "eliminar")
    private static String sqlEliminarReserva;

    @SqlStatement(namespace = "reserva", value = "existe")
    private static String sqlExisteReserva;

    @SqlStatement(namespace = "reserva", value = "existePorId")
    private static String sqlExisteReservaPorId;

    @SqlStatement(namespace = "reserva", value = "cancelarReserva")
    private static String sqlCancelarReserva;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrearReserva);
    }

    @Override
    public void actualizar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizarReserva);
    }

    @Override
    public void cancelarReserva(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCancelarReserva, paramSource);

    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarReserva, paramSource);
    }

    @Override
    public boolean existe(LocalDateTime fecha, Long idCancha) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);
        paramSource.addValue("idCancha", idCancha);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteReserva, paramSource, Boolean.class);
    }


    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteReservaPorId, paramSource, Boolean.class);
    }
}
