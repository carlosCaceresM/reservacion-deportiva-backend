SELECT COUNT(1) FROM reserva
WHERE fecha = :fecha
AND id_cancha = :idCancha
AND estado = :estado