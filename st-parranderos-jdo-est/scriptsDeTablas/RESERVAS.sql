

  CREATE TABLE "ISIS2304C021910"."RESERVAS" 
   (	"NUM_RESERVA" NUMBER(3,0), 
	"ID_ESTADIA" NUMBER(3,0), 
	"ID_SERVICIO" NUMBER(3,0), 
	"ID_HORARIO" NUMBER(3,0), 
	"ID_CONSUMO" NUMBER(3,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;



  CREATE UNIQUE INDEX "ISIS2304C021910"."RESERVAS_PK" ON "ISIS2304C021910"."RESERVAS" ("NUM_RESERVA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;


  ALTER TABLE "ISIS2304C021910"."RESERVAS" MODIFY ("ID_CONSUMO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."RESERVAS" MODIFY ("ID_HORARIO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."RESERVAS" MODIFY ("ID_SERVICIO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."RESERVAS" MODIFY ("ID_ESTADIA" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."RESERVAS" MODIFY ("NUM_RESERVA" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."RESERVAS" ADD CONSTRAINT "RESERVAS_PK" PRIMARY KEY ("NUM_RESERVA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD"  ENABLE;
  ALTER TABLE "ISIS2304C021910"."RESERVAS" ADD CONSTRAINT "NOM_RESERVAS_NO_VACIO" CHECK (ID_CONSUMO > 0) ENABLE;


  ALTER TABLE "ISIS2304C021910"."RESERVAS" ADD CONSTRAINT "FK_ID_ESTADIA" FOREIGN KEY ("ID_ESTADIA")
	  REFERENCES "ISIS2304C021910"."ESTADIAS" ("ID_ESTADIA") ENABLE;
  ALTER TABLE "ISIS2304C021910"."RESERVAS" ADD CONSTRAINT "FK_ID_HORARIO_RESERVA" FOREIGN KEY ("ID_HORARIO")
	  REFERENCES "ISIS2304C021910"."HORARIOS" ("ID_HORARIO") ENABLE;
  ALTER TABLE "ISIS2304C021910"."RESERVAS" ADD CONSTRAINT "FK_ID_SERVICIO_RES" FOREIGN KEY ("ID_SERVICIO")
	  REFERENCES "ISIS2304C021910"."SERVICIOS" ("ID_SERVICIO") ENABLE;
