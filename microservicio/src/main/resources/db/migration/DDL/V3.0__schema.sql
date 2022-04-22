create table cancha (
	 id int primary key auto_increment,
	 nombre varchar(20) not null,
	 id_tipo_cancha int not null,
	 	foreign key (id_tipo_cancha) references tipo_cancha(id)
);

INSERT INTO cancha(nombre, id_tipo_cancha) VALUES ('numero 1',1);
INSERT INTO cancha(nombre, id_tipo_cancha) VALUES ('numero 2',1);
INSERT INTO cancha(nombre, id_tipo_cancha) VALUES ('numero 3',1);
INSERT INTO cancha(nombre, id_tipo_cancha) VALUES ('numero 4',1);
INSERT INTO cancha(nombre, id_tipo_cancha) VALUES ('numero 5',2);
INSERT INTO cancha(nombre, id_tipo_cancha) VALUES ('numero 6',2);
