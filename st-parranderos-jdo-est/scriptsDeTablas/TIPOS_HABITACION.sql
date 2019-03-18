

  CREATE TABLE "ISIS2304C021910"."TIPOS_HABITACION" 
   (	"ID_TIPO_HABITACION" NUMBER(3,0), 
	"DESCRIPCION" VARCHAR2(250 BYTE), 
	"CAPACIDAD" NUMBER(1,0), 
	"PRECIO_POR_NOCHE" NUMBER(6,0), 
	"ID_HOTEL" NUMBER(3,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;



  CREATE UNIQUE INDEX "ISIS2304C021910"."TIPO_HABITACION_PK" ON "ISIS2304C021910"."TIPOS_HABITACION" ("ID_TIPO_HABITACION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;


  ALTER TABLE "ISIS2304C021910"."TIPOS_HABITACION" ADD CONSTRAINT "TIPO_HABITACION_PK" PRIMARY KEY ("ID_TIPO_HABITACION")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD"  ENABLE;
  ALTER TABLE "ISIS2304C021910"."TIPOS_HABITACION" MODIFY ("ID_HOTEL" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."TIPOS_HABITACION" MODIFY ("PRECIO_POR_NOCHE" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."TIPOS_HABITACION" MODIFY ("CAPACIDAD" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."TIPOS_HABITACION" MODIFY ("DESCRIPCION" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."TIPOS_HABITACION" MODIFY ("ID_TIPO_HABITACION" NOT NULL ENABLE);


  ALTER TABLE "ISIS2304C021910"."TIPOS_HABITACION" ADD CONSTRAINT "FK_ID_HOTEL" FOREIGN KEY ("ID_HOTEL")
	  REFERENCES "ISIS2304C021910"."HOTELES" ("ID_HOTEL") ENABLE;
