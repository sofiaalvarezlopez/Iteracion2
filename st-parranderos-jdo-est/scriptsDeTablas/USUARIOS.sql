

  CREATE TABLE "ISIS2304C021910"."USUARIOS" 
   (	"NUMERO_DOCUMENTO" NUMBER(11,0), 
	"TIPO_DOCUMENTO" VARCHAR2(2 BYTE), 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"CORREO_ELECTRONICO" VARCHAR2(20 BYTE), 
	"ID_TIPO_USUARIO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;


  CREATE UNIQUE INDEX "ISIS2304C021910"."USUARIOS_PK" ON "ISIS2304C021910"."USUARIOS" ("TIPO_DOCUMENTO", "NUMERO_DOCUMENTO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;


  ALTER TABLE "ISIS2304C021910"."USUARIOS" ADD CONSTRAINT "USUARIOS_PK" PRIMARY KEY ("TIPO_DOCUMENTO", "NUMERO_DOCUMENTO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD"  ENABLE;
  ALTER TABLE "ISIS2304C021910"."USUARIOS" MODIFY ("ID_TIPO_USUARIO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."USUARIOS" MODIFY ("CORREO_ELECTRONICO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."USUARIOS" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."USUARIOS" MODIFY ("TIPO_DOCUMENTO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."USUARIOS" MODIFY ("NUMERO_DOCUMENTO" NOT NULL ENABLE);
  ALTER TABLE "ISIS2304C021910"."USUARIOS" ADD CONSTRAINT "NUM_DOC_POSITIVO" CHECK (NUMERO_DOCUMENTO > 0) ENABLE;
  ALTER TABLE "ISIS2304C021910"."USUARIOS" ADD CONSTRAINT "NOMBRE_USUARIO_NO_VACIO" CHECK (NOMBRE <> '') ENABLE;
  ALTER TABLE "ISIS2304C021910"."USUARIOS" ADD CONSTRAINT "CORREO_ADECUADO" CHECK (CORREO_ELECTRONICO LIKE '%@%') ENABLE;
  ALTER TABLE "ISIS2304C021910"."USUARIOS" ADD CONSTRAINT "CK_TIPO_DOCUMENTO" CHECK (TIPO_DOCUMENTO IN ('TI','CC','CE','RC')) ENABLE;


  ALTER TABLE "ISIS2304C021910"."USUARIOS" ADD CONSTRAINT "FK_ID_TIPO_USUARIO" FOREIGN KEY ("ID_TIPO_USUARIO")
	  REFERENCES "ISIS2304C021910"."TIPO_USUARIO" ("ID_TIPO_USUARIO") ENABLE;
