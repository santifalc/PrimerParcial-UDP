create table datos_meteorologicos(
id_estacion integer not null,
ciudad varchar(100),
porcentaje_humedad float,
temperatura int,
velocidad_viento float,
fecha varchar(100),
hora varchar(20),
constraint id_estacion_pk primary key (id_estacion)
);