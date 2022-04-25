UPDATE reserva
SET	nombre_usuario = :nombreUsuario,
	fecha = :fecha,
    horas_reservadas =:horasReservadas,
	valor_pagar = :valorPagar,
	estado = :estado,
    id_cancha = :idCancha
WHERE id = :id