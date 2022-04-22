create table tipo_cancha (
	 id int primary key,
	 nombre varchar(20) not null,
	 tarifa decimal not null,
	 descuento int not null
);

INSERT INTO tipo_cancha VALUES (1,'Futbol',60000,20);
INSERT INTO tipo_cancha VALUES (2,'Voleibol',30000,10);
