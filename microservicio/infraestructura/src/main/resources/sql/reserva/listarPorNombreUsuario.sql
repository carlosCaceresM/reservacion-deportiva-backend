SELECT
	r.id,
	r.nombre_usuario,
	r.fecha,r.horas_reservadas,
	r.valor_pagar,
	r.estado,c.nombre AS cancha
FROM reserva AS r
INNER JOIN cancha AS c ON c.id = r.id_cancha
WHERE r.nombre_usuario = :nombreUsuario
AND r.id_cancha = :idCancha
AND r.estado = true