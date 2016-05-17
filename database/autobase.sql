--------------------------------------------------------
--  File created - Monday-May-16-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence CARS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CARS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence DRIVERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DRIVERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence JOURNAL_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "JOURNAL_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence ROUTES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROUTES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Table CARS
--------------------------------------------------------

  CREATE TABLE "CARS" ("ID" NUMBER, "CAR_NUMBER" VARCHAR2(20), "COLOR" VARCHAR2(20), "BRAND" VARCHAR2(20), "READY" VARCHAR2(20), "DRIVER_ID" NUMBER)
--------------------------------------------------------
--  DDL for Table DRIVERS
--------------------------------------------------------

  CREATE TABLE "DRIVERS" ("ID" NUMBER, "NAME" VARCHAR2(20), "SURNAME" VARCHAR2(20), "GENDER" VARCHAR2(20), "PHONE" NUMBER)
--------------------------------------------------------
--  DDL for Table JOURNAL
--------------------------------------------------------

  CREATE TABLE "JOURNAL" ("ID" NUMBER, "DATE_OUT" DATE, "DATE_IN" DATE, "ROUTE_ID" NUMBER, "CAR_ID" NUMBER)
--------------------------------------------------------
--  DDL for Table ROUTES
--------------------------------------------------------

  CREATE TABLE "ROUTES" ("ID" NUMBER, "ROUTE_NAME" VARCHAR2(20), "LENGTH" NUMBER, "PRICE" NUMBER)
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "USERS" ("ID" NUMBER, "LOGIN" VARCHAR2(20), "PASSWORD" VARCHAR2(20), "ROLE" VARCHAR2(20))
--------------------------------------------------------
--  DDL for Index CARS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CARS_PK" ON "CARS" ("ID")
--------------------------------------------------------
--  DDL for Index DRIVERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DRIVERS_PK" ON "DRIVERS" ("ID")
--------------------------------------------------------
--  DDL for Index JOURNAL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "JOURNAL_PK" ON "JOURNAL" ("ID")
--------------------------------------------------------
--  DDL for Index ROUTES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROUTES_PK" ON "ROUTES" ("ID")
--------------------------------------------------------
--  DDL for Index USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "USERS_PK" ON "USERS" ("ID")
--------------------------------------------------------
--  DDL for Trigger CARS_DEL_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CARS_DEL_TRG" BEFORE DELETE ON AUTOBASE.CARS
FOR EACH ROW
DECLARE
  tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM AUTOBASE.JOURNAL
  WHERE :old.ID = CAR_ID;
  IF(tmp > 0) THEN
    raise_application_error(-20001,'Can not delete this auto!');
  END IF;
END;
ALTER TRIGGER "CARS_DEL_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger CARS_INS_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CARS_INS_TRG" BEFORE INSERT ON AUTOBASE.CARS
FOR EACH ROW
DECLARE
tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM AUTOBASE.CARS WHERE CAR_NUMBER=:new.CAR_NUMBER;
  IF tmp>0 THEN
  raise_application_error(-20001,'Car with this number already exists!');
  END IF;
END;
ALTER TRIGGER "CARS_INS_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger CARS_SEQ_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CARS_SEQ_TRG" BEFORE INSERT ON AUTOBASE.CARS
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT CARS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "CARS_SEQ_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger CARS_UPD_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CARS_UPD_TRG" BEFORE UPDATE ON AUTOBASE.CARS
FOR EACH ROW
BEGIN
  IF :new.CAR_NUMBER<>:old.CAR_NUMBER THEN
  :new.CAR_NUMBER:=:old.CAR_NUMBER;
  END IF;
END;
ALTER TRIGGER "CARS_UPD_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger DRIVERS_DEL_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "DRIVERS_DEL_TRG" BEFORE DELETE ON AUTOBASE.DRIVERS
FOR EACH ROW
DECLARE
  tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM AUTOBASE.CARS
  WHERE :old.ID = DRIVER_ID;
  IF(tmp > 0) THEN
    raise_application_error(-20001,'Can not delete this person!');
  END IF;
END;
ALTER TRIGGER "DRIVERS_DEL_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger DRIVERS_SEQ_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "DRIVERS_SEQ_TRG" BEFORE INSERT ON AUTOBASE.DRIVERS
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT DRIVERS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "DRIVERS_SEQ_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger JOURNAL_INS_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "JOURNAL_INS_TRG" BEFORE INSERT ON AUTOBASE.JOURNAL
FOR EACH ROW
DECLARE
tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM AUTOBASE.JOURNAL WHERE DATE_IN IS NULL AND :new.CAR_ID = CAR_ID;
  IF tmp>0 THEN
  raise_application_error(-20001,'Auto is not back yet!');
  END IF;
END;
ALTER TRIGGER "JOURNAL_INS_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger JOURNAL_SEQ_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "JOURNAL_SEQ_TRG" BEFORE INSERT ON AUTOBASE.JOURNAL
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT JOURNAL_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "JOURNAL_SEQ_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger JOURNAL_UPD_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "JOURNAL_UPD_TRG" BEFORE UPDATE ON AUTOBASE.JOURNAL
FOR EACH ROW
BEGIN
  IF :new.DATE_IN < :old.DATE_OUT THEN
  raise_application_error(-20001,'Error! Date in less then date out!');
  END IF;
END;
ALTER TRIGGER "JOURNAL_UPD_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger ROUTES_DEL_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "ROUTES_DEL_TRG" BEFORE DELETE ON AUTOBASE.ROUTES
FOR EACH ROW
DECLARE
  tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM AUTOBASE.JOURNAL
  WHERE :old.ID = ROUTE_ID;
  IF(tmp > 0) THEN
    raise_application_error(-20001,'Can not delete this route!');
  END IF;
END;
ALTER TRIGGER "ROUTES_DEL_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger ROUTES_SEQ_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "ROUTES_SEQ_TRG" BEFORE INSERT ON AUTOBASE.ROUTES
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT ROUTES_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "ROUTES_SEQ_TRG" ENABLE
--------------------------------------------------------
--  DDL for Trigger USERS_SEQ_TRG
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "USERS_SEQ_TRG" BEFORE INSERT ON AUTOBASE.USERS
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT USERS_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
ALTER TRIGGER "USERS_SEQ_TRG" ENABLE
