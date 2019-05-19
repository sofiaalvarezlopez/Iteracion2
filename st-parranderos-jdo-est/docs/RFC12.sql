--Mejor cliente trimestre
SELECT ANIO, IDCLIENTE, COUNT (VECES) AS TRIMESTRES
FROM
    (SELECT to_char(estadias.fechallegada - 7/24,'IYYY') AS ANIO, to_char(estadias.fechallegada  - 7/24,'Q') AS trimestre, idcliente, COUNT(idcliente) AS VECES
    FROM ESTADIAS
    where IDCLIENTE IS NOT null
    group by to_char(estadias.fechallegada  - 7/24,'IYYY'), to_char(estadias.fechallegada  - 7/24,'Q'), idcliente)
   GROUP BY ANIO, IDCLIENTE 
   HAVING COUNT (VECES) = 4;
-- Los que siempre compran un servicio de minimo 300k
   SELECT idcliente, MIN(consumido) AS MINIMO_CONSUMIDO
   FROM (
   SELECT estadias.idcliente,  MAX(PRECIO) AS CONSUMIDO
   FROM FACTURAS, EStadias
   WHERE Facturas.idestadia = estadias.idestadia
   GROUP BY facturas.IDESTADIA, estadias.idcliente)
   GROUP BY idcliente
   HAVING MIN(consumido) > 300000;
--servios de spa con reserva de mayor 4h
   SELECT idcliente, MIN(MAX_ESTADIA) AS MINIMO_CONSUMIDO
   FROM (SELECT IDCLIENTE, NOMBRESERVICIO, IDESTADIA, MAX(TIEMPO) AS MAX_ESTADIA
      FROM(SELECT Estadias.idCliente, servicios.nombreservicio, estadias.idEstadia, horarios.fechainicio, horarios.fechafin, horarios.horainicio, horarios.horaFin
          ,CASE WHEN FECHAINICIO <> FECHAFIN THEN 24
          ELSE TO_NUMBER(HORAFIN) - TO_NUMBER(HORAINICIO) END AS TIEMPO
          FROM  SERVICIOS, EStadias, RESERVAS, HORARIOS
          WHERE reservas.idEstadia = estadias.idestadia
          AND reservas.idhorario = horarios.idhorario
          AND servicios.idservicio = reservas.idservicio
          AND NOMBRESERVICIO = 'SPA')
      GROUP BY IDCLIENTE, NOMBRESERVICIO, IDESTADIA
   )
   GROUP BY IDCLIENTE, NOMBRESERVICIO
   HAVING MIN(MAX_ESTADIA) >= 4;
 
 
   
 