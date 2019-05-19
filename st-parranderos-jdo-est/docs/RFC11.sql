--Mejor servicio por semana
select ANIO, SEMANA,IDservicio, NombreServicio, VECES
from (
  select ANIO, SEMANA,IDservicio, nombreServicio, VECES,
    row_number() over (partition by anio, semana order by veces desc) as rn
  from (
    SELECT to_char(facturas.fecha - 7/24,'IYYY') AS ANIO, to_char(facturas.fecha - 7/24,'IW') AS SEMANA, servicioS.IDservicio, SERVICIOS.NOMBRESERVICIO, COUNT(sERVICIOS.IDSERVICIO) AS VECES
    FROM FACTURAS,SERVICIOS
    WHERE facturas.idservicio = servicios.idservicio group by to_char(facturas.fecha - 7/24,'IYYY'), to_char(facturas.fecha - 7/24,'IW'), servicioS.IDservicio, SERVICIOS.NOMBRESERVICIO
  )
)
where rn = 1;
--servicio menos consumido
select ANIO, SEMANA,IDservicio, NombreServicio, VECES
from (
  select ANIO, SEMANA,IDservicio, nombreServicio, VECES,
    row_number() over (partition by anio, semana order by veces asc) as rn
  from (
    SELECT to_char(facturas.fecha - 7/24,'IYYY') AS ANIO, to_char(facturas.fecha - 7/24,'IW') AS SEMANA, servicioS.IDservicio, SERVICIOS.NOMBRESERVICIO, COUNT(sERVICIOS.IDSERVICIO) AS VECES
    FROM FACTURAS,SERVICIOS
    WHERE facturas.idservicio = servicios.idservicio group by to_char(facturas.fecha - 7/24,'IYYY'), to_char(facturas.fecha - 7/24,'IW'), servicioS.IDservicio, SERVICIOS.NOMBRESERVICIO
  )
)
where rn = 1;
--habitacion mas solicitada
select ANIO, SEMANA, IDHABITACION, VECES
from (
  select ANIO, SEMANA, IDHABITACION, VECES,
    row_number() over (partition by anio, semana order by veces desc) as rn
  from (
    SELECT to_char(estadias.fechallegada - 7/24,'IYYY') AS ANIO, to_char(estadias.fechallegada - 7/24,'IW') AS SEMANA, idhabitacion, COUNT(idHabitacion) AS VECES
    FROM ESTADIAS
    group by to_char(estadias.fechallegada - 7/24,'IYYY'), to_char(estadias.fechallegada - 7/24,'IW'), idHabitacion
  )
)
where rn = 1;
--habitacion mas solicitada
select ANIO, SEMANA, IDHABITACION, VECES
from (
  select ANIO, SEMANA, IDHABITACION, VECES,
    row_number() over (partition by anio, semana order by veces asc) as rn
  from (
    SELECT to_char(estadias.fechallegada - 7/24,'IYYY') AS ANIO, to_char(estadias.fechallegada - 7/24,'IW') AS SEMANA, idhabitacion, COUNT(idHabitacion) AS VECES
    FROM ESTADIAS
    group by to_char(estadias.fechallegada - 7/24,'IYYY'), to_char(estadias.fechallegada - 7/24,'IW'), idHabitacion
  )
)
where rn = 1;