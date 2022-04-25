package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombreUsuario = resultSet.getString("nombre_usuario");
        LocalDateTime fecha = extraerLocalDateTime(resultSet, "fecha");
        int horasReservadas = resultSet.getInt("horas_reservadas");
        double valorPagar = resultSet.getDouble("valor_pagar");
        boolean estado = resultSet.getBoolean("estado");
        String cancha = resultSet.getString("cancha");

        return new DtoReserva(id, nombreUsuario, fecha, horasReservadas, valorPagar, estado, cancha);
    }

}
