
  CREATE TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" 
   (	"ID_CARACTERISTICA_ADICIONAL" NUMBER(3,0), 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"VALOR" NUMBER(8,0), 
	"ID_SERVICIO_ADICIONAL" NUMBER(3,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;


  CREATE UNIQUE INDEX "ISIS2304C021910"."CARACTERISTICA_ADICIONAL_PK" ON "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" ("ID_CARACTERISTICA_ADICIONAL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;

  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" ADD CONSTRAINT "VALOR_CA_POSITIVO" CHECK (VALOR > 0) ENABLE;
  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" MODIFY ("ID_SERVICIO_ADICIONAL" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" MODIFY ("VALOR" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" MODIFY ("ID_CARACTERISTICA_ADICIONAL" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" ADD CONSTRAINT "NOMBRE_CA_NO_VACIO" CHECK (NOMBRE <> '') ENABLE;
  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" ADD CONSTRAINT "CARACTERISTICA_ADICIONAL_PK" PRIMARY KEY ("ID_CARACTERISTICA_ADICIONAL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD"  ENABLE;


  ALTER TABLE "ISIS2304C021910"."CARACTERISTICAS_ADICIONALES" ADD CONSTRAINT "FK_ID_SERVICIO_ADD" FOREIGN KEY ("ID_SERVICIO_ADICIONAL")
	  REFERENCES "ISIS2304C021910"."SERVICIOS_ADICIONALES" ("ID_SERVICIO") ENABLE;
