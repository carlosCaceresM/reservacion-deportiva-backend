create table reserva (
	 id int primary key auto_increment,
	 nombre_usuario varchar(50) not null,
	 fecha datetime not null,
	 horas_reservadas int not null,
	 valor_pagar decimal not null,
	 estado boolean not null,
	 id_cancha int not null,
	 	foreign key (id_cancha) references cancha(id)
);