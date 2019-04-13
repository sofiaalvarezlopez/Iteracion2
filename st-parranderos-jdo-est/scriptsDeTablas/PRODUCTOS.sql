

  CREATE TABLE "ISIS2304C021910"."PRODUCTOS" 
   (	"ID_PRODUCTOS" NUMBER(3,0), 
	"PRECIO" NUMBER(6,0), 
	"NOMBRE" VARCHAR2(20 BYTE), 
	"CANTIDAD" NUMBER(2,0), 
	"DURACION" NUMBER(2,0), 
	"CATEGORIA" VARCHAR2(50 BYTE), 
	"ID_VENTA_PRODUCTO" NUMBER(3,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;


  CREATE UNIQUE INDEX "ISIS2304C021910"."PRODUCTOS_PK" ON "ISIS2304C021910"."PRODUCTOS" ("ID_PRODUCTOS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;


  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" MODIFY ("ID_VENTA_PRODUCTO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" MODIFY ("PRECIO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" MODIFY ("ID_PRODUCTOS" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" ADD CONSTRAINT "PRODUCTOS_PK" PRIMARY KEY ("ID_PRODUCTOS")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD"  ENABLE;
  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" ADD CONSTRAINT "PRECIO_POSITIVO" CHECK (PRECIO > 0) ENABLE;
  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" ADD CONSTRAINT "NOMBRE_PROD_NO_VACIO" CHECK (NOMBRE <> '') ENABLE;
  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" ADD CONSTRAINT "CATEGORIA_NO_VACIA" CHECK (CATEGORIA <> '') ENABLE;
  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" ADD CONSTRAINT "CANT_PROD" CHECK (CANTIDAD > 0) ENABLE;


  ALTER TABLE "ISIS2304C021910"."PRODUCTOS" ADD CONSTRAINT "FK_ID_PRODUCTOS" FOREIGN KEY ("ID_VENTA_PRODUCTO")
	  REFERENCES "ISIS2304C021910"."VENTA_PRODUCTOS" ("ID_SERVICIO") ENABLE;
