--------------------------------------------------------
--  File created - vasárnap-május-18-2025
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table AJANLOTT_VIDEO
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."AJANLOTT_VIDEO"
(	"EMAIL" VARCHAR2(100 BYTE),
     "VIDEOID" NUMBER,
     "HOZZAADVA" DATE DEFAULT SYSDATE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FELHASZNALO
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."FELHASZNALO"
(	"EMAIL" VARCHAR2(100 BYTE),
     "FELHASZNALONEV" VARCHAR2(100 BYTE),
     "JELSZO" VARCHAR2(100 BYTE),
     "SZEREPKORID" NUMBER,
     "AVATARURL" VARCHAR2(1000 BYTE),
     "LAST_LOGIN" DATE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FELTOLTI
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."FELTOLTI"
(	"VIDEOID" NUMBER,
     "EMAIL" VARCHAR2(100 BYTE),
     "FELTOLTES_IDEJE" DATE DEFAULT SYSDATE
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table HOZZAAD
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."HOZZAAD"
(	"VIDEOID" NUMBER,
     "PLAYLISTID" NUMBER
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table HOZZASZOLAS
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."HOZZASZOLAS"
(	"COMMENTID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
     "VIDEOID" NUMBER,
     "EMAIL" VARCHAR2(100 BYTE),
     "TARTALOM" VARCHAR2(1000 BYTE)
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KEDVELI
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."KEDVELI"
(	"EMAIL" VARCHAR2(100 BYTE),
     "VIDEOID" NUMBER
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LEJATSZASI_LISTA
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."LEJATSZASI_LISTA"
(	"PLAYLISTID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
     "EMAIL" VARCHAR2(100 BYTE),
     "PLAYLISTNEV" VARCHAR2(255 BYTE)
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LETREHOZ
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."LETREHOZ"
(	"EMAIL" VARCHAR2(100 BYTE),
     "PLAYLISTID" NUMBER
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MEGIR
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."MEGIR"
(	"EMAIL" VARCHAR2(100 BYTE),
     "COMMENTID" NUMBER
) SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table NEZ
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."NEZ"
(	"VIEWID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
     "EMAIL" VARCHAR2(100 BYTE),
     "VIDEOID" NUMBER
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SZEREPE
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."SZEREPE"
(	"EMAIL" VARCHAR2(100 BYTE),
     "SZEREPKORID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE
) SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SZEREPKOR
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."SZEREPKOR"
(	"SZEREPKORID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
     "SZEREPKORNEV" VARCHAR2(100 BYTE)
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TARTOZIK
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."TARTOZIK"
(	"COMMENTID" NUMBER,
     "VIDEOID" NUMBER
) SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table VIDEO
--------------------------------------------------------

CREATE TABLE "VIDEOFLOW"."VIDEO"
(	"VIDEOID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
     "VIDEOCIM" VARCHAR2(255 BYTE),
     "KATEGORIA" VARCHAR2(100 BYTE),
     "MEGTEKINTES_SZAM" NUMBER DEFAULT 0,
     "KULCSSZO" VARCHAR2(255 BYTE),
     "LEIRAS" VARCHAR2(1000 BYTE),
     "FILE_PATH" VARCHAR2(500 BYTE),
     "USERNAME" VARCHAR2(100 BYTE)
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into VIDEOFLOW.AJANLOTT_VIDEO
SET DEFINE OFF;
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('sara@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('sara@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('sara@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('sara@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('akos@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('akos@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('akos@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('akos@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('vera@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('vera@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('vera@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('vera@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bori@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bori@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bori@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bori@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('gabor@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('gabor@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('gabor@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('gabor@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bence@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bence@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bence@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('bence@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('virag@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('virag@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('virag@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('virag@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('anna@gmail.com','14',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('anna@gmail.com','15',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('anna@gmail.com','16',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.AJANLOTT_VIDEO (EMAIL,VIDEOID,HOZZAADVA) values ('anna@gmail.com','17',to_date('25-MÁJ.  -17','RR-MON-DD'));
REM INSERTING into VIDEOFLOW.FELHASZNALO
SET DEFINE OFF;
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('anna@gmail.com','Anna','$2a$10$QFmfiDTeGhaqQapZdTE3YO3Ys1vItdHAQ7xJiZbjV2Q5gnKdZdjvm','3',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('marina@gmail.com','Marina','$2a$10$HLMvNih05FKevIc0XNwDYOCE2S4sYXZ18GB8xOl40yILaogQbb4gS','2',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('csaba@gmail.com','Csaba','$2a$10$6gkhHBkPaIPLZhd1SptQL.ZCCpAtacegQWe3Jh4501UeGLjjyd7C.','3',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('david@gmail.com','David','password123','4',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('erika@gmail.com','Erika2','123','4',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('qwe@gmail.com','qwe','$2a$10$gwMSBv7Ju8XyB/8yiPYfu.cmRjvALJefx0HVtrLE0PBOsJYh/t5Xi','1',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('user@gmail.com','user','$2a$10$6gkhHBkPaIPLZhd1SptQL.ZCCpAtacegQWe3Jh4501UeGLjjyd7C.','1',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('asd@gmail.com','asd','$2a$10$LP8rIGqFwAYVwtMlbjnF5u15vuZLSvfUUe9lzeCU9bWk4Y/y8Xsua','1',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('contentc@gmail.com','contentc','$2a$10$kF58o0zCh.FkZRkXrLqut.ELEdX4zlaEusvUst8f6LcIJzEBy02PG','2',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('drcode@gmail.com','drcode','$2a$10$X1.I8jqgHf2k1SmdP/iKPeZ5bAF2ZqIPkR4N5Qsfr3CiZ.XcZrOYu','2',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('chef@gmail.com','chef','$2a$10$zkO.uPkq9eU7.XhtilPqlOMelGVcv3Abc2VwIl85TMy.7Iov.F8Jy','2',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('virag@gmail.com','virag','$2a$10$jhJKLCeV8VKtrJbCm5JjmOJ4R1YrFGp6Ti369TPCmd0bJQj1Z1JIC','1',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('marcell@gmail.com','marcell','$2a$10$Ekq0thkqn45HAEAX5x0hJeeafk9oRnu6Roqyelm2rVY43Z5X4TieK','2',null,null);
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('gabor@gmail.com','gabor','$2a$10$fZ9TAXzuCEkdqJM08.lWL.46yz5FKU/oKEGrZxgYpt/gTtfsqy8vu','3',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('bori@gmail.com','bori','$2a$10$/nwZOS6z4Sm7dRAF3toSuuO8SDm59p4zsvwtIerkcqrekiDKiMPOi','4',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('vera@gmail.com','vera','$2a$10$jyY3B98OLZbpoT3ucJkIBOb82C26BZHMKgZQbKpiXSSZWl1vXw8ky','5',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('sara@gmail.com','sara','$2a$10$cTm1kyAMZbxcP8xUFPBVJuiwd1jglFrSMGhQVNycUtKkBpnvXgOeC','1',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('akos@gmail.com','akos','$2a$10$yfmcQF9HzsrCiQSS.5ax1.n4JSWrFz7uSo0xOrRaCNlxRJ3MOrmwi','2',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('bence@gmail.com','bence','$2a$10$NpnfNUmZbsbe3acSgukyR.lEqxNJdj4qDkz7IH2SXKjuyKznnY/MW','3',null,to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELHASZNALO (EMAIL,FELHASZNALONEV,JELSZO,SZEREPKORID,AVATARURL,LAST_LOGIN) values ('emma@gmail.com','emma','$2a$10$ZmL1X5yMsxyhbzpw/LZjA.7uKtbD3AGf32JS0dmXjoTWxskWfRpaO','4',null,null);
REM INSERTING into VIDEOFLOW.FELTOLTI
SET DEFINE OFF;
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('16','chef@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('14','drcode@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('15','drcode@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('17','chef@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('18','marina@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('19','marina@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('20','marina@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('21','marcell@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('22','marcell@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('23','marcell@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
Insert into VIDEOFLOW.FELTOLTI (VIDEOID,EMAIL,FELTOLTES_IDEJE) values ('24','akos@gmail.com',to_date('25-MÁJ.  -17','RR-MON-DD'));
REM INSERTING into VIDEOFLOW.HOZZAAD
SET DEFINE OFF;
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('14','27');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('14','30');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('15','27');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('15','30');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('18','29');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('19','29');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('20','29');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('21','28');
Insert into VIDEOFLOW.HOZZAAD (VIDEOID,PLAYLISTID) values ('24','31');
REM INSERTING into VIDEOFLOW.HOZZASZOLAS
SET DEFINE OFF;
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('6','15','user@gmail.com','really useful');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('7','14','user@gmail.com','i love your videos');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('8','24','virag@gmail.com','beautiful :)');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('9','21','virag@gmail.com','how interesting');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('10','23','virag@gmail.com','nyc !');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('12','19','bori@gmail.com','so cute');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('14','18','gabor@gmail.com','amazing!');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('16','17','vera@gmail.com',':)');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('17','24','vera@gmail.com','so pretty');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('18','21','vera@gmail.com','nice');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('11','23','bori@gmail.com','best time of the day');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('13','17','bori@gmail.com','looks good');
Insert into VIDEOFLOW.HOZZASZOLAS (COMMENTID,VIDEOID,EMAIL,TARTALOM) values ('15','22','gabor@gmail.com',':)');
REM INSERTING into VIDEOFLOW.KEDVELI
SET DEFINE OFF;
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('akos@gmail.com','18');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('bence@gmail.com','17');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('bence@gmail.com','18');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('bence@gmail.com','24');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('bori@gmail.com','19');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('bori@gmail.com','23');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('gabor@gmail.com','15');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('gabor@gmail.com','18');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('gabor@gmail.com','22');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('marina@gmail.com','16');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('marina@gmail.com','20');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('marina@gmail.com','22');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('marina@gmail.com','23');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('user@gmail.com','14');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('user@gmail.com','15');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('vera@gmail.com','17');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('vera@gmail.com','21');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('vera@gmail.com','24');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('virag@gmail.com','21');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('virag@gmail.com','23');
Insert into VIDEOFLOW.KEDVELI (EMAIL,VIDEOID) values ('virag@gmail.com','24');
REM INSERTING into VIDEOFLOW.LEJATSZASI_LISTA
SET DEFINE OFF;
Insert into VIDEOFLOW.LEJATSZASI_LISTA (PLAYLISTID,EMAIL,PLAYLISTNEV) values ('27','user@gmail.com','study');
Insert into VIDEOFLOW.LEJATSZASI_LISTA (PLAYLISTID,EMAIL,PLAYLISTNEV) values ('28','virag@gmail.com','travelling');
Insert into VIDEOFLOW.LEJATSZASI_LISTA (PLAYLISTID,EMAIL,PLAYLISTNEV) values ('31','bence@gmail.com','akos feltöltött videói');
Insert into VIDEOFLOW.LEJATSZASI_LISTA (PLAYLISTID,EMAIL,PLAYLISTNEV) values ('30','gabor@gmail.com','Kategoria: education');
Insert into VIDEOFLOW.LEJATSZASI_LISTA (PLAYLISTID,EMAIL,PLAYLISTNEV) values ('29','akos@gmail.com','Marina feltöltött videói');
REM INSERTING into VIDEOFLOW.LETREHOZ
SET DEFINE OFF;
Insert into VIDEOFLOW.LETREHOZ (EMAIL,PLAYLISTID) values ('akos@gmail.com','29');
Insert into VIDEOFLOW.LETREHOZ (EMAIL,PLAYLISTID) values ('bence@gmail.com','31');
Insert into VIDEOFLOW.LETREHOZ (EMAIL,PLAYLISTID) values ('gabor@gmail.com','30');
Insert into VIDEOFLOW.LETREHOZ (EMAIL,PLAYLISTID) values ('user@gmail.com','27');
Insert into VIDEOFLOW.LETREHOZ (EMAIL,PLAYLISTID) values ('virag@gmail.com','28');
REM INSERTING into VIDEOFLOW.MEGIR
SET DEFINE OFF;
REM INSERTING into VIDEOFLOW.NEZ
SET DEFINE OFF;
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('51',null,'17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('49','marina@gmail.com','16');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('50',null,'16');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('52',null,'20');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('53',null,'22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('54',null,'23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('55','user@gmail.com','16');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('56','user@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('57','user@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('58','user@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('59','user@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('60','user@gmail.com','14');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('61','user@gmail.com','14');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('62','user@gmail.com','14');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('63','user@gmail.com','14');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('64','virag@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('68','virag@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('70','virag@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('71','virag@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('72','marina@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('73','marina@gmail.com','22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('74','marina@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('75','marina@gmail.com','20');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('82','sara@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('83','sara@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('86','bori@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('87','bori@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('88','bori@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('89','bori@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('98','bori@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('99','bori@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('104','gabor@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('105','gabor@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('106','gabor@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('107','gabor@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('110','gabor@gmail.com','22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('111','gabor@gmail.com','22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('114','gabor@gmail.com','22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('115','gabor@gmail.com','22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('118','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('119','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('120','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('121','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('132','vera@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('133','vera@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('136','bence@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('137','bence@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('144','bence@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('145','bence@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('65','virag@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('66','virag@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('67','virag@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('69','virag@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('84','bori@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('85','bori@gmail.com','23');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('100','gabor@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('101','gabor@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('108','gabor@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('109','gabor@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('124','vera@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('125','vera@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('90','bori@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('112','gabor@gmail.com','22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('113','gabor@gmail.com','22');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('116','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('117','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('91','bori@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('146','bence@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('147','bence@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('148','bence@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('149','bence@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('130','vera@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('92','bori@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('93','bori@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('131','vera@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('140','bence@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('141','bence@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('94','bori@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('95','bori@gmail.com','19');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('96','bori@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('97','bori@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('102','gabor@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('103','gabor@gmail.com','15');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('122','vera@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('123','vera@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('126','vera@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('127','vera@gmail.com','24');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('128','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('129','vera@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('134','vera@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('76','akos@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('135','vera@gmail.com','21');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('138','bence@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('139','bence@gmail.com','18');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('142','bence@gmail.com','17');
Insert into VIDEOFLOW.NEZ (VIEWID,EMAIL,VIDEOID) values ('143','bence@gmail.com','17');
REM INSERTING into VIDEOFLOW.SZEREPE
SET DEFINE OFF;
REM INSERTING into VIDEOFLOW.SZEREPKOR
SET DEFINE OFF;
Insert into VIDEOFLOW.SZEREPKOR (SZEREPKORID,SZEREPKORNEV) values ('3','admin');
Insert into VIDEOFLOW.SZEREPKOR (SZEREPKORID,SZEREPKORNEV) values ('2','content_creator');
Insert into VIDEOFLOW.SZEREPKOR (SZEREPKORID,SZEREPKORNEV) values ('1','felhasznalo');
Insert into VIDEOFLOW.SZEREPKOR (SZEREPKORID,SZEREPKORNEV) values ('5','kitiltott');
Insert into VIDEOFLOW.SZEREPKOR (SZEREPKORID,SZEREPKORNEV) values ('4','szuperadmin');
REM INSERTING into VIDEOFLOW.TARTOZIK
SET DEFINE OFF;
REM INSERTING into VIDEOFLOW.VIDEO
SET DEFINE OFF;
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('16','From Dough to Delicious: Crafting the Perfect Pizza','food','3','#pizza #cooking #food','Get hungry as we take you through the step-by-step process of making a mouthwatering pizza from scratch. Crispy crust, rich sauce, and melty cheese—this is pizza done right!','uploads/1747486159473_1746388683800_3944332-uhd_4096_2160_25fps.mp4','chef@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('14','Learn Something New: Educational Video','education','5','#code #learning','Curious minds, this one’s for you! Packed with surprising and useful knowledge, this short educational clip delivers bite-sized learning in an entertaining way.','uploads/1747486027735_1746388156627_1422633-hd_1920_810_24fps.mp4','drcode@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('15','Code in Action: Building Something Cool'',''education','education','6','#code #learning','Watch a simple idea come to life through code! Whether you’re a beginner or just love seeing programming in motion, this short video shows how fun and creative coding can be.','uploads/1747486074151_1746388216640_2278095-hd_1920_1080_30fps.mp4','drcode@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('17','Simple Ingredients, Big Flavor: A Cozy Home-Cooked Meal','food','10','#cooking #dinner #recipe','Discover how easy and rewarding home cooking can be. This video guides you through a comforting, flavor-packed recipe perfect for a weeknight dinner or a lazy weekend treat.','uploads/1747486236258_2620043-uhd_3840_2160_25fps.mp4','chef@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('18','Dancers of the Deep: Jellyfish in Motion','animals','6','#jellyfish #ocean #biology #animal','Float into the mesmerizing world of jellyfish with this short visual journey. Watch as these ethereal creatures glide effortlessly through the water, their delicate movements and glowing forms capturing the mystery and beauty of the ocean. A calming glimpse into one of nature’s most elegant drifters.','uploads/1747486328764_856882-hd_1920_1080_24fps.mp4','marina@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('19','Tall and Graceful: A Day in the Life of a Giraffe','animals','5','#giraffe #animal','Join us for a peaceful look into the life of one of nature’s most unique animals. Watch this gentle giant roam, graze, and interact in its natural habitat—majestic and calm, just like the savannah it calls home.','uploads/1747486380904_2178545-hd_1280_720_24fps.mp4','marina@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('20','Elegance on Water: The Silent Beauty of Swans','animals','2','#swans #animal #lake #bird','Glide alongside these graceful birds as they move with quiet majesty across the water. This serene video captures the timeless elegance and peaceful presence of swans in their natural setting.','uploads/1747486423258_854066-hd_1920_1080_30fps.mp4','marina@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('21','Desert Monolith: Echoes of Time','travel','7','#desert #ancient #travel','Towering alone in the golden sands, this grand structure stands as a testament to resilience and ancient craftsmanship beneath the desert sun.','uploads/1747486521707_4361420-uhd_3840_2160_25fps.mp4','marcell@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('22','Calm Before the Storm: Ocean Stillness','travel','8','#travel #ocean #storm','Dark clouds gather on the horizon, but the sea remains still—an eerie and beautiful moment of suspense before nature unleashes its power.','uploads/1747486563732_12182219_3840_2160_30fps.mp4','marcell@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('23','City Lights After Dark','travel','7','#city #night','When the sun goes down, the city comes alive. A glowing skyline and bustling streets reflect the rhythm and energy of urban nightlife.','uploads/1747486607430_12290716_3840_2160_24fps.mp4','marcell@gmail.com');
Insert into VIDEOFLOW.VIDEO (VIDEOID,VIDEOCIM,KATEGORIA,MEGTEKINTES_SZAM,KULCSSZO,LEIRAS,FILE_PATH,USERNAME) values ('24','Sunset Voyage: Birds Over Calm Waters','travel','7','#travel #ocean #birds','Sail into serenity as birds glide across a glowing horizon. This tranquil sunset at sea captures nature’s quiet poetry in motion.','uploads/1747486676174_13536349_3840_2160_60fps.mp4','akos@gmail.com');
--------------------------------------------------------
--  DDL for Trigger PLAYLIST_LETREHOZO
--------------------------------------------------------

CREATE OR REPLACE EDITIONABLE TRIGGER "VIDEOFLOW"."PLAYLIST_LETREHOZO"
AFTER INSERT ON LETREHOZ
FOR EACH ROW
BEGIN
UPDATE LEJATSZASI_LISTA SET EMAIL = :NEW.EMAIL
WHERE LEJATSZASI_LISTA.playlistid = :NEW.playlistid;
END;
/
ALTER TRIGGER "VIDEOFLOW"."PLAYLIST_LETREHOZO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRG_AFTER_LOGIN
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "VIDEOFLOW"."TRG_AFTER_LOGIN"
AFTER UPDATE OF LAST_LOGIN ON FELHASZNALO
    FOR EACH ROW
BEGIN
  -- Töröljük a korábbi ajánlásokat
DELETE FROM AJANLOTT_VIDEO
WHERE EMAIL = :NEW.EMAIL;

-- Ajánljunk 4 videót, amiket még nem kedvelt a felhasználó
INSERT INTO AJANLOTT_VIDEO (EMAIL, VIDEOID)
SELECT :NEW.EMAIL, v.VIDEOID
FROM VIDEO v
WHERE v.VIDEOID NOT IN (
    SELECT k.VIDEOID FROM KEDVELI k WHERE k.EMAIL = :NEW.EMAIL
)
  AND ROWNUM <= 4;
END;

/
ALTER TRIGGER "VIDEOFLOW"."TRG_AFTER_LOGIN" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRG_LIMIT_COMMENT_LENGTH
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "VIDEOFLOW"."TRG_LIMIT_COMMENT_LENGTH"
BEFORE INSERT ON HOZZASZOLAS
FOR EACH ROW
BEGIN
    IF LENGTH(:NEW.TARTALOM) > 500 THEN
        :NEW.TARTALOM := SUBSTR(:NEW.TARTALOM, 1, 500);
END IF;
END;

/
ALTER TRIGGER "VIDEOFLOW"."TRG_LIMIT_COMMENT_LENGTH" ENABLE;
--------------------------------------------------------
--  DDL for Trigger VIDEO_FELTOLTO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "VIDEOFLOW"."VIDEO_FELTOLTO"
AFTER INSERT ON FELTOLTI
FOR EACH ROW
BEGIN
UPDATE VIDEO SET USERNAME = :NEW.EMAIL
WHERE VIDEO.VIDEOID = :NEW.VIDEOID;
END;
/
ALTER TRIGGER "VIDEOFLOW"."VIDEO_FELTOLTO" ENABLE;
--------------------------------------------------------
--  DDL for Procedure ADD_VIEW_AND_INCREMENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "VIDEOFLOW"."ADD_VIEW_AND_INCREMENT" (
    p_email    IN VARCHAR2,
    p_videoid  IN NUMBER
)
AS
BEGIN
    -- Néző rögzítése
INSERT INTO nez (email, videoid) VALUES (p_email, p_videoid);

-- Megtekintésszám növelése
UPDATE video SET megtekintes_szam = megtekintes_szam + 1 WHERE videoid = p_videoid;
END;

/
--------------------------------------------------------
--  DDL for Procedure FELTOLTES_MENTES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "VIDEOFLOW"."FELTOLTES_MENTES" (
    p_videocim IN VARCHAR2,
    p_kategoria IN VARCHAR2,
    p_kulcsszo IN VARCHAR2,
    p_leiras IN VARCHAR2,
    p_file_path IN VARCHAR2,
    p_email IN VARCHAR2
) AS
    v_videoid NUMBER;
BEGIN
INSERT INTO video (videocim, kategoria, kulcsszo, leiras, file_path, megtekintes_szam)
VALUES (p_videocim, p_kategoria, p_kulcsszo, p_leiras, p_file_path, 0)
    RETURNING videoid INTO v_videoid;

INSERT INTO feltolti (videoid, email, feltoltes_ideje)
VALUES (v_videoid, p_email, SYSDATE);
END;

/
--------------------------------------------------------
--  DDL for Procedure KOMMENTEK_SZAMA_VIDEOHOZ
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "VIDEOFLOW"."KOMMENTEK_SZAMA_VIDEOHOZ" (
    p_videoid IN NUMBER,
    p_szam OUT NUMBER
) AS
BEGIN
SELECT COUNT(*) INTO p_szam FROM hozzaszolas WHERE videoid = p_videoid;
END;

/
--------------------------------------------------------
--  Constraints for Table VIDEO
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."VIDEO" MODIFY ("VIDEOID" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."VIDEO" MODIFY ("VIDEOCIM" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."VIDEO" ADD PRIMARY KEY ("VIDEOID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table HOZZASZOLAS
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."HOZZASZOLAS" MODIFY ("COMMENTID" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."HOZZASZOLAS" ADD PRIMARY KEY ("COMMENTID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table KEDVELI
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."KEDVELI" ADD PRIMARY KEY ("EMAIL", "VIDEOID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FELHASZNALO
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."FELHASZNALO" MODIFY ("FELHASZNALONEV" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."FELHASZNALO" MODIFY ("JELSZO" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."FELHASZNALO" ADD PRIMARY KEY ("EMAIL")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
ALTER TABLE "VIDEOFLOW"."FELHASZNALO" ADD UNIQUE ("FELHASZNALONEV")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SZEREPE
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."SZEREPE" MODIFY ("SZEREPKORID" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."SZEREPE" ADD PRIMARY KEY ("EMAIL")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SZEREPKOR
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."SZEREPKOR" MODIFY ("SZEREPKORID" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."SZEREPKOR" MODIFY ("SZEREPKORNEV" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."SZEREPKOR" ADD PRIMARY KEY ("SZEREPKORID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
ALTER TABLE "VIDEOFLOW"."SZEREPKOR" ADD UNIQUE ("SZEREPKORNEV")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TARTOZIK
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."TARTOZIK" ADD PRIMARY KEY ("COMMENTID", "VIDEOID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FELTOLTI
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."FELTOLTI" ADD PRIMARY KEY ("VIDEOID", "EMAIL")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table HOZZAAD
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."HOZZAAD" ADD PRIMARY KEY ("VIDEOID", "PLAYLISTID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LETREHOZ
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."LETREHOZ" ADD PRIMARY KEY ("EMAIL", "PLAYLISTID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MEGIR
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."MEGIR" ADD PRIMARY KEY ("EMAIL", "COMMENTID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table AJANLOTT_VIDEO
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."AJANLOTT_VIDEO" MODIFY ("EMAIL" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."AJANLOTT_VIDEO" MODIFY ("VIDEOID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table LEJATSZASI_LISTA
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."LEJATSZASI_LISTA" MODIFY ("PLAYLISTID" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."LEJATSZASI_LISTA" ADD PRIMARY KEY ("PLAYLISTID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table NEZ
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."NEZ" MODIFY ("VIEWID" NOT NULL ENABLE);
ALTER TABLE "VIDEOFLOW"."NEZ" ADD PRIMARY KEY ("VIEWID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table AJANLOTT_VIDEO
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."AJANLOTT_VIDEO" ADD CONSTRAINT "FK_AJANLOTT_FELHASZNALO" FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FELTOLTI
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."FELTOLTI" ADD FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HOZZAAD
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."HOZZAAD" ADD FOREIGN KEY ("PLAYLISTID")
    REFERENCES "VIDEOFLOW"."LEJATSZASI_LISTA" ("PLAYLISTID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HOZZASZOLAS
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."HOZZASZOLAS" ADD FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table KEDVELI
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."KEDVELI" ADD CONSTRAINT "FK_KEDVELI_FELHASZNALO" FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table LEJATSZASI_LISTA
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."LEJATSZASI_LISTA" ADD FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table LETREHOZ
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."LETREHOZ" ADD FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
ALTER TABLE "VIDEOFLOW"."LETREHOZ" ADD FOREIGN KEY ("PLAYLISTID")
    REFERENCES "VIDEOFLOW"."LEJATSZASI_LISTA" ("PLAYLISTID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MEGIR
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."MEGIR" ADD FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
ALTER TABLE "VIDEOFLOW"."MEGIR" ADD FOREIGN KEY ("COMMENTID")
    REFERENCES "VIDEOFLOW"."HOZZASZOLAS" ("COMMENTID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table NEZ
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."NEZ" ADD FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE SET NULL ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SZEREPE
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."SZEREPE" ADD FOREIGN KEY ("EMAIL")
    REFERENCES "VIDEOFLOW"."FELHASZNALO" ("EMAIL") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TARTOZIK
--------------------------------------------------------

ALTER TABLE "VIDEOFLOW"."TARTOZIK" ADD FOREIGN KEY ("COMMENTID")
    REFERENCES "VIDEOFLOW"."HOZZASZOLAS" ("COMMENTID") ON DELETE CASCADE ENABLE;
