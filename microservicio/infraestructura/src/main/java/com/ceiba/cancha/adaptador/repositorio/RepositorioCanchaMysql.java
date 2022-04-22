package com.ceiba.cancha.adaptador.repositorio;

import com.ceiba.cancha.modelo.entidad.Cancha;
import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioCanchaMysql implements RepositorioCancha {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cancha", value = "crear")
    private static String sqlCrearCancha;

    @SqlStatement(namespace = "cancha", value = "actualizar")
    private static String sqlActualizarCancha;

    @SqlStatement(namespace = "cancha", value = "eliminar")
    private static String sqlEliminarCancha;

    @SqlStatement(namespace = "cancha", value = "existe")
    private static String sqlExisteCancha;

    @SqlStatement(namespace = "cancha", value = "existePorId")
    private static String sqlExisteCanchaPorId;

    public RepositorioCanchaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Cancha cancha) {
        return this.customNamedParameterJdbcTemplate.crear(cancha, sqlCrearCancha);
    }

    @Override
    public void actualizar(Cancha cancha) {
        this.customNamedParameterJdbcTemplate.actualizar(cancha, sqlActualizarCancha);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarCancha, paramSource);
    }

    @Override
    public boolean existe(String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteCancha, paramSource, Boolean.class);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteCanchaPorId, paramSource, Boolean.class);
    }
}
