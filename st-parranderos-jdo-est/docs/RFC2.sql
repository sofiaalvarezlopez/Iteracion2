-- RFC2 TOTAL RECOLECTADO POR SERVICIO (USAR PARAMETROS)
SELECT A.IDSERVICIO, SUM(PRECIO) AS TOTAL_RECOLECTADO
FROM 
((SELECT * FROM ISIS2304C021910.FACTURAS
WHERE FECHA > TO_DATE('2019-01-01', 'YYYY-MM-DD') AND FECHA < TO_DATE('2019-12-31', 'YYYY-MM-DD'))A
INNER JOIN ISIS2304C021910.SERVICIOS
ON A.IDSERVICIO = ISIS2304C021910.SERVICIOS.IDSERVICIO)
GROUP BY A.IDSERVICIO
ORDER BY SUM(PRECIO) DESC
FETCH FIRST 20 ROWS ONLY;

