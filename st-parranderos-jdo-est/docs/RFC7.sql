--Parte 1
SELECT IDCLIENTE, SUM(PRECIO)  
FROM FACTURAS, ESTADIAS
WHERE FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA AND facturas.fecha BETWEEN '01/01/23' AND '31/12/23'
GROUP BY IDCLIENTE
HAVING SUM (PRECIO) > 15000000;


--Parte 2
SELECT IDCLIENTE, SUM(TOTAL) AS TOTALDIAS
FROM(SELECT IDCLIENTE, (FECHASALIDA - FECHALLEGADA ) as TOTAL FROM ESTADIAS WHERE FECHASALIDA BETWEEN '01/01/18' AND '31/12/18')
GROUP BY IDCLIENTE
HAVING SUM(TOTAL) > 14;