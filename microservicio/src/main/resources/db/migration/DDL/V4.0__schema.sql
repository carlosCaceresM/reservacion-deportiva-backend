create table reserva (
	 id int primary key auto_increment,
	 nombre_usuario varchar(50) not null,
	 fecha_inicio datetime not null,
	 fecha_final datetime not null,
	 valor_pagar decimal not null,
	 estado boolean not null,
	 id_cancha int not null,
	 	foreign key (id_cancha) references cancha(id)
);