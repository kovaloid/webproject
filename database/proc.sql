 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 ---   �������������
 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 --Hints: select, where, count, max, group by, having, like, create view, drop view
 
--1.	������� �������������, ������������ ���� ���������
CREATE OR REPLACE VIEW KOVAL.VIEW_1 AS 
SELECT FIRST_NAME, LAST_NAME, FATHER_NAME FROM KOVAL.AUTO_PERSONNEL;

--2.	������� �������������, ������������ ���� ���������, ������� ������� ���������� �� �������� �����
CREATE OR REPLACE VIEW KOVAL.VIEW_2 AS 
SELECT FIRST_NAME, LAST_NAME, FATHER_NAME FROM KOVAL.AUTO_PERSONNEL
WHERE LAST_NAME LIKE '�%';

--3.	������� �������������, ������������ ���� ��������� � ���������� ����������� �������
CREATE OR REPLACE VIEW KOVAL.VIEW_3 AS 
SELECT PERSONNEL_ID, FIRST_NAME, LAST_NAME, COUNT(AUTO_PERSONNEL.ID) AS NUMBER_OF_AUTO FROM KOVAL.AUTO 
JOIN KOVAL.AUTO_PERSONNEL ON PERSONNEL_ID = AUTO_PERSONNEL.ID
GROUP BY PERSONNEL_ID, FIRST_NAME, LAST_NAME;

--4.	������� �������������, ������������ ��� �������� � ����������, ������� �� ��� ��������� � �������� ���� �� �������� ����
CREATE OR REPLACE VIEW KOVAL.VIEW_4 AS 
SELECT NAME, NUM, MARK FROM KOVAL.ROUTES 
JOIN KOVAL.JOURNAL ON ROUTE_ID = ROUTES.ID
JOIN KOVAL.AUTO ON AUTO.ID = AUTO_ID
WHERE TIME_OUT > '28-MAR-15 08.26.39.080000000 AM' AND TIME_IN < '16-MAY-15 12.00.00.000000000 AM'
ORDER BY NAME;

--5.	������� �������������, ������������ ��� �������� � ���������� ����������� ����������� �� ������ ��������
CREATE OR REPLACE VIEW KOVAL.VIEW_5(NAME,NUMBER_OF_AUTO) AS 
SELECT NAME, COUNT(AUTO.ID) FROM KOVAL.ROUTES 
JOIN KOVAL.JOURNAL ON ROUTE_ID = ROUTES.ID
JOIN KOVAL.AUTO ON AUTO.ID = AUTO_ID
GROUP BY NAME
ORDER BY NAME;

 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 ---   �������� ���������
 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 --Hints: select, where, count, max, group by, having, create procedure, drop procedure
 
--�	��� ����������
--1.	������� �������� ���������, ��������� ���������, � ������� ���������� ���������� ����������� � �����.
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_11 IS
BEGIN
FOR r_row IN ( 
SELECT PERSONNEL_ID p, FIRST_NAME f, LAST_NAME l FROM KOVAL.AUTO 
JOIN KOVAL.AUTO_PERSONNEL ON PERSONNEL_ID = AUTO_PERSONNEL.ID
GROUP BY PERSONNEL_ID, FIRST_NAME, LAST_NAME
HAVING COUNT(PERSONNEL_ID) = (SELECT MAX(NUMBER_OF_AUTO) FROM (SELECT COUNT(PERSONNEL_ID) AS NUMBER_OF_AUTO FROM KOVAL.AUTO 
GROUP BY PERSONNEL_ID))
) LOOP
dbms_output.put_line(r_row.p||'     '||r_row.f||'     '||r_row.l);
END LOOP;
END;

--2.	������� �������� ���������, ��������� ����� ���������� ���� � �����
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_12 IS
BEGIN
FOR r_row IN ( 
SELECT COLOR s FROM KOVAL.AUTO
GROUP BY COLOR
HAVING COUNT(COLOR) = (SELECT MAX(POPULAR_COLOR) FROM (SELECT COUNT(COLOR) AS POPULAR_COLOR FROM KOVAL.AUTO GROUP BY COLOR))
) LOOP
dbms_output.put_line(r_row.s);
END LOOP;
END;

--3.	������� �������� ���������, ��������� ��� �������� � ������� ����� ������� �� ��� � �������
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_13 IS
BEGIN
FOR r_row IN ( 
SELECT ROUTE_ID r, NAME n, AVG(sysdate + (TIME_IN - TIME_OUT)*24*60 - sysdate) AS MIDDLE_TIME_IN_MINUTES FROM KOVAL.JOURNAL
JOIN KOVAL.ROUTES ON ROUTE_ID = ROUTES.ID
GROUP BY ROUTE_ID, NAME
ORDER BY ROUTE_ID
) LOOP
dbms_output.put_line(r_row.r||'     '||r_row.n||'     '||r_row.MIDDLE_TIME_IN_MINUTES);
END LOOP;
END;

--�	� �������� �����������
--1.	������� �������� ��������� � ���������� ������� � ��������� ��� ����������, ���������� �� ����� ��������.
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_21
(
  ROUTE_ID_PARAM IN NUMBER 
) IS 
BEGIN
FOR r_row IN ( 
SELECT DISTINCT AUTO_ID i, MARK m FROM KOVAL.JOURNAL
JOIN KOVAL.AUTO ON AUTO.ID = AUTO_ID
WHERE ROUTE_ID = ROUTE_ID_PARAM
ORDER BY AUTO_ID
) LOOP
dbms_output.put_line(r_row.i||'     '||r_row.m);
END LOOP;
END;

--2.	������� �������� ���������, ������� �������� ���������� � ��������� ��������, � ������� �� ���.
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_22
(
  AUTO_ID_PARAM IN NUMBER 
) IS 
BEGIN
FOR r_row IN ( 
SELECT DISTINCT ROUTE_ID i, NAME n FROM KOVAL.JOURNAL
JOIN KOVAL.ROUTES ON ROUTES.ID = ROUTE_ID
WHERE AUTO_ID = AUTO_ID_PARAM
ORDER BY ROUTE_ID
) LOOP
dbms_output.put_line(r_row.i||'     '||r_row.n);
END LOOP;
END;

--3.	������� �������� ���������, ������� ��� ��������� ����������1 �  ����������2. ��� ������ ���������� ��������, �� ������� ����������1 ������� ������� ����������2. ���� �� �����-���� �������� ���� �� ����������� �� ��������, ����� ������� �� ���������������.
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_23
(
  AUTO_ID_PARAM1 IN NUMBER,
  AUTO_ID_PARAM2 IN NUMBER
) IS 
BEGIN
FOR r_row IN ( 
SELECT R1 AS ROUTE_ID, NAME n FROM (SELECT ROUTE_ID R1, (TIME_IN - TIME_OUT) AS DELTA1 FROM KOVAL.JOURNAL WHERE AUTO_ID = AUTO_ID_PARAM1)
JOIN (SELECT ROUTE_ID R2, (TIME_IN - TIME_OUT) AS DELTA2 FROM KOVAL.JOURNAL WHERE AUTO_ID = AUTO_ID_PARAM2) ON R1 = R2
JOIN KOVAL.ROUTES ON R1 = ROUTES.ID
WHERE DELTA1 > DELTA2
) LOOP
dbms_output.put_line(r_row.ROUTE_ID||'     '||r_row.n);
END LOOP;
END;

--�	� ��������� �����������
--1.	������� �������� ��������� � ������� ���������� ����, �������������� ���������� ����������� ����� �����.
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_31
(
  COLOR_PARAM IN VARCHAR
, COUNTER_AUTO OUT NUMBER
) IS 
BEGIN
SELECT COUNT(*) INTO COUNTER_AUTO FROM KOVAL.AUTO
WHERE COLOR = COLOR_PARAM;
END;

--2.	������� �������� ��������� � ������� ���������� ���� � �������� ���������� � ���������� ����������� ����������� � ���
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_32
(
  ROUTE_ID_PARAM IN NUMBER
, COUNTER_AUTO OUT NUMBER
) IS 
BEGIN
SELECT COUNT(*) INTO COUNTER_AUTO FROM KOVAL.JOURNAL
WHERE ROUTE_ID = ROUTE_ID_PARAM;
END;

--3.	������� �������� ��������� � ������� ���������� ������� � ����� ��������� �����������, ������������� ����� �������� ����� ������� �� ��������� �������� � ����������, ����������� ������
CREATE OR REPLACE PROCEDURE KOVAL.PROCEDURE_33
(
  ROUTE_ID_PARAM IN NUMBER 
, BEST_TIME OUT NUMBER
, BEST_AUTO OUT NUMBER
) IS 
BEGIN
SELECT AUTO_ID, (sysdate + (TIME_IN - TIME_OUT)*24*60*60 - sysdate) AS btime_in_sec INTO BEST_AUTO, BEST_TIME FROM 
    (SELECT * FROM KOVAL.JOURNAL WHERE JOURNAL.ROUTE_ID = ROUTE_ID_PARAM ORDER BY (TIME_IN - TIME_OUT)) WHERE ROWNUM=1;
END;

 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 ---   ��������
 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 --Hints: select, where, in, exists, join, commit, rollback, create trigger, drop trigger
 
--�	�������� �� �������
--1.	������� �������, ������� �� ��������� �������� ���������� � �������, ������� ��� ���� � �����
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_11
BEFORE INSERT ON KOVAL.AUTO
FOR EACH ROW
DECLARE
tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM KOVAL.AUTO WHERE NUM=:new.NUM;
  IF tmp>0 THEN
  raise_application_error(-20001,'Car with this number already exists!');
  END IF;
END;

--2.	������� �������, ������� �� ��������� �������� ������ � ������ ���������, � ������� ���� �������� �� NULL
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_12
BEFORE INSERT ON KOVAL.JOURNAL
FOR EACH ROW
DECLARE
tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM KOVAL.JOURNAL WHERE :new.TIME_IN IS NOT NULL;
  IF tmp>0 THEN
  raise_application_error(-20001,'Time in is not null!');
  END IF;
END;

--3.	������� �������, ������� �� ��������� ��������� � ���� ����������, ������� ��� �� �������� � ����
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_13
BEFORE INSERT ON KOVAL.JOURNAL
FOR EACH ROW
DECLARE
tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM KOVAL.JOURNAL WHERE TIME_IN IS NULL AND :new.AUTO_ID = AUTO_ID;
  IF tmp>0 THEN
  raise_application_error(-20001,'Auto is not back!');
  END IF;
END;

--�	�������� �� �����������
--1.	������� �������, �� ����������� �������� ����� ����������
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_21
BEFORE UPDATE ON KOVAL.AUTO
FOR EACH ROW
BEGIN
  IF :new.NUM<>:old.NUM THEN
  :new.NUM:=:old.NUM;
  END IF;
END;

--2.	������� �������, ������� �� �������� �������� �������, � ������� ���� ������������� �����
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_22
BEFORE UPDATE ON KOVAL.JOURNAL
FOR EACH ROW
BEGIN
  IF :new.ROUTE_ID<>:old.ROUTE_ID AND :old.TIME_IN is null THEN
  :new.ROUTE_ID:=:old.ROUTE_ID;
  END IF;
END;

--3.	������� �������, ������� ��� ��������� ��������, � ������ ������� ������ �� ������� ������ ����� �������, �������� ������������ ������� �������� � ��������� ��� ������ �� ����. ����� �������, ������ ������ ������� ����� �� �������� ��������� �� �������, �� �������� ��� ���� �������.
--CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_23
--BEFORE UPDATE ON KOVAL.ROUTES
--FOR EACH ROW
--DECLARE
--tmp NUMBER;
--BEGIN
--  SELECT COUNT(*) into tmp FROM KOVAL.JOURNAL WHERE ROUTE_ID = :new.ID;
--  IF tmp>0 THEN
--    raise_application_error(-20001,'YES!');
--  ELSE
--    raise_application_error(-20001,'NO!');
--  END IF;
--END;

--4.	������� �������, ������� �� �������� ���������� ����� �������� ������� ��������� ������, ��� ����� ��������
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_24
BEFORE UPDATE ON KOVAL.JOURNAL
FOR EACH ROW
BEGIN
  IF :new.TIME_IN < :old.TIME_OUT THEN
  raise_application_error(-20001,'Time in error!');
  END IF;
END;

--�	�������� �� ��������
--1.	������� �������, ������� ��� �������� �������� � ������ ������� �� ���� ������ ���������� ����������
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_31
BEFORE DELETE ON KOVAL.ROUTES
FOR EACH ROW
DECLARE
  tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM KOVAL.JOURNAL
  WHERE :old.ID = ROUTE_ID;
  IF(tmp > 0) THEN
    raise_application_error(-20001,'Can not delete this route!');
  END IF;
END;

--2.	������� �������, ������� ��� �������� ���������� � ������ ������� �� ���� ������ ���������� ����������
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_32
BEFORE DELETE ON KOVAL.AUTO
FOR EACH ROW
DECLARE
  tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM KOVAL.JOURNAL
  WHERE :old.ID = AUTO_ID;
  IF(tmp > 0) THEN
    raise_application_error(-20001,'Can not delete this auto!');
  END IF;
END;

--3.	������� �������, ������� ��� �������� �������� � ������ ������� �� ���� ������ ���������� ����������
CREATE OR REPLACE TRIGGER KOVAL.TRIGGER_33
BEFORE DELETE ON KOVAL.AUTO_PERSONNEL
FOR EACH ROW
DECLARE
  tmp NUMBER;
BEGIN
  SELECT COUNT(*) INTO tmp FROM KOVAL.AUTO
  WHERE :old.ID = PERSONNEL_ID;
  IF(tmp > 0) THEN
    raise_application_error(-20001,'Can not delete this person!');
  END IF;
END;
