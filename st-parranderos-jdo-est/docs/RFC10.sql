--SENTENCIA B?SICA PARA EL GERENTE Y RECEPECIONISTAS
SELECT *
FROM USUARIOS
WHERE numerodocumento
NOT IN
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE)
AND idtipousuario = 5;
--ORDENADO POR NUMDOCASC
SELECT *
FROM USUARIOS
WHERE numerodocumento
NOT IN
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE)
AND idtipousuario = 5
ORDER BY numerodocumento;
--ORDENADO POR NUMDOCDESC
SELECT *
FROM USUARIOS
WHERE numerodocumento
NOT IN
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE)
AND idtipousuario = 5
ORDER BY numerodocumento DESC;
--ORDENADO POR NOMBREASC
SELECT *
FROM USUARIOS
WHERE numerodocumento
NOT IN
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE)
AND idtipousuario = 5
ORDER BY NOMBRE;
--ORDENADO POR NOMBREDESC
SELECT *
FROM USUARIOS
WHERE numerodocumento
NOT IN
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019'
GROUP BY NUMERODOCUMENTO, NOMBRE)
AND idtipousuario = 5
ORDER BY NOMBRE DESC;
--SENTENCIA B?SICA PARA EL ORGANIZADOR
SELECT USUARIOS.NOMBRE, usuarios.numerodocumento,  usuarios.correoelectronico, convenciones.nombre as NOMBRE_CONVENCION
FROM USUARIOS,ESTADIAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND numeroDocumento NOT IN 
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019')
AND idtipousuario = 5;
--ORDENADO POR NOMBRE ASC
SELECT USUARIOS.NOMBRE, usuarios.numerodocumento,  usuarios.correoelectronico, convenciones.nombre as NOMBRE_CONVENCION
FROM USUARIOS,ESTADIAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND numeroDocumento NOT IN 
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019')
AND idtipousuario = 5
ORDER BY USUARIOS.NOMBRE;
--ORDENADO POR NOMBRE DESC
SELECT USUARIOS.NOMBRE, usuarios.numerodocumento,  usuarios.correoelectronico, convenciones.nombre as NOMBRE_CONVENCION
FROM USUARIOS,ESTADIAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND numeroDocumento NOT IN 
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019')
AND idtipousuario = 5
ORDER BY USUARIOS.NOMBRE DESC;
--ORDENADO POR NOMBRECONVENCION ASC
SELECT USUARIOS.NOMBRE, usuarios.numerodocumento,  usuarios.correoelectronico, convenciones.nombre as NOMBRE_CONVENCION
FROM USUARIOS,ESTADIAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND numeroDocumento NOT IN 
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019')
AND idtipousuario = 5
ORDER BY CONVENCIONES.NOMBRE;
--ORDENADO POR NOMBRECONVENCION DESC
SELECT USUARIOS.NOMBRE, usuarios.numerodocumento,  usuarios.correoelectronico, convenciones.nombre as NOMBRE_CONVENCION
FROM USUARIOS,ESTADIAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND numeroDocumento NOT IN 
(
SELECT NUMERODOCUMENTO
FROM USUARIOS,ESTADIAS,FACTURAS,CONVENCIONES
WHERE usuarios.numerodocumento = estadias.idcliente
AND  estadias.idestadia = facturas.idestadia
AND estadias.idconvencion = convenciones.idconvencion
AND convenciones.idorganizador = 19135679
AND facturas.fecha BETWEEN '01/01/2019' AND '31/12/2019')
AND idtipousuario = 5
ORDER BY CONVENCIONES.NOMBRE DESC;
