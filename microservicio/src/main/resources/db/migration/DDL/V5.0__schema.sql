create table parametro_tarifa (
	 id int primary key,
	 nombre varchar(50) not null,
	 valor decimal not null
);

INSERT INTO parametro_tarifa VALUES (1,'Tarifa global',70000);