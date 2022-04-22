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
    private static String sqlCrear;

    @SqlStatement(namespace = "cancha", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "cancha", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "cancha", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "cancha", value = "existePorId")
    private static String sqlExistePorId;

    public RepositorioCanchaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Cancha cancha) {
        return this.customNamedParameterJdbcTemplate.crear(cancha, sqlCrear);
    }

    @Override
    public void actualizar(Cancha cancha) {
        this.customNamedParameterJdbcTemplate.actualizar(cancha, sqlActualizar);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExiste, paramSource, Boolean.class);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorId, paramSource, Boolean.class);
    }
}
