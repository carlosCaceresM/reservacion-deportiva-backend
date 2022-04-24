package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public interface RepositorioReserva {

    Long crear(Reserva cancha);

    void actualizar(Reserva cancha);

    void eliminar(Long id);

    boolean existe(LocalDateTime fecha, Long idCancha, boolean estado);

    boolean existePorId(Long id);

}
