--SENTENCIA BÁSICA PARA EL GERENTE Y RECEPECIONISTAS
SELECT NUMERODOCUMENTO, nombre, COUNT(numfactura) AS NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE
--ORDENADO POR NUMDOCASC
SELECT usuarios.NUMERODOCUMENTO, usuarios.nombre, COUNT(numfactura) AS NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE
ORDER BY numerodocumento;
--ORDENADO POR NUMDOCDESC
SELECT NUMERODOCUMENTO, nombre, COUNT(numfactura) AS NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE
ORDER BY numerodocumento DESC;
--ORDENADO POR NOMBREASC
SELECT NUMERODOCUMENTO, nombre, COUNT(numfactura) AS NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE
ORDER BY nombre;
--ORDENADO POR NOMBREDESC
SELECT NUMERODOCUMENTO, nombre, COUNT(numfactura) AS NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE
ORDER BY nombre DESC;
--SENTENCIA BÁSICA PARA EL ORGANIZADOR
SELECT NUMERODOCUMENTO, USUARIOS.nombre, count(numfactura) as NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
AND idtipousuario = 5
GROUP BY NUMERODOCUMENTO, USUARIOS.nombre;
--ORDENADO POR NOMBRE ASC
SELECT NUMERODOCUMENTO, USUARIOS.nombre, count(numfactura) as NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
AND idtipousuario = 5
GROUP BY NUMERODOCUMENTO, USUARIOS.nombre
ORDER BY USUARIOS.nombre;
--ORDENADO POR NOMBRE DESC
SELECT NUMERODOCUMENTO, USUARIOS.nombre, count(numfactura) as NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
AND idtipousuario = 5
GROUP BY NUMERODOCUMENTO, USUARIOS.nombre
ORDER BY USUARIOS.nombre DESC;
--ORDENADO POR NOMBRECONVENCION ASC
SELECT NUMERODOCUMENTO, USUARIOS.nombre, convenciones.nombre, count(numfactura) as NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
AND idtipousuario = 5
GROUP BY NUMERODOCUMENTO, USUARIOS.nombre, convenciones.nombre
ORDER BY CONVENCIONES.NOMBRE;
--ORDENADO POR NOMBRECONVENCION DESC
SELECT NUMERODOCUMENTO, USUARIOS.nombre, convenciones.nombre, count(numfactura) as NUM_FACTURAS
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
AND idtipousuario = 5
GROUP BY NUMERODOCUMENTO, USUARIOS.nombre, convenciones.nombre
ORDER BY CONVENCIONES.NOMBRE DESC;
