SELECT c.id, c.nombre, tp.nombre as tipo_cancha, tp.tarifa
FROM cancha as c
inner join tipo_cancha as tp on tp.id = id_tipo_cancha
WHERE id_tipo_cancha = :idTipoCancha;