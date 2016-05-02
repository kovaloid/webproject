 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 ---   ������� ������
 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 --Hints: select, count, join, where, in, exists, order by, group by, having
 
--�	������������� �������
--1.	������� �������� ���� ���������
SELECT NAME FROM KOVAL.ROUTES;
--2.	������� ������ ����������� ��������� �����
SELECT NUM FROM KOVAL.AUTO WHERE COLOR = '�����';
--3.	������� ��� ���������, ���������� � ���������� ������� �� �������
SELECT LAST_NAME, FIRST_NAME, FATHER_NAME FROM KOVAL.AUTO_PERSONNEL ORDER BY LAST_NAME ASC;
--4.	������� ��� ���������, ���������� � ���������� ������� �� ������� � � �������� ������� �� �����
SELECT LAST_NAME, FIRST_NAME, FATHER_NAME FROM KOVAL.AUTO_PERSONNEL ORDER BY LAST_NAME ASC, FIRST_NAME DESC;
--5.	������� ���� ������� �� ��� ������� ������� ���������, ���� ������ ������� ������ ��������� ����
SELECT TIME_IN FROM KOVAL.JOURNAL WHERE TIME_OUT > '31-MAY-14';
--6.	������� ������ �����������, � ������� ������ ����� ������ ��
SELECT NUM FROM KOVAL.AUTO WHERE NUM LIKE '�%';
--7.	��������� ���������� �����������, � ������� ������ ����� ������ ��
SELECT COUNT(*) AS P_COUNTER FROM KOVAL.AUTO WHERE NUM LIKE '�%';

--�	������� � ������������
--1.	������� ������ ����������� �������� � �������� ��������
SELECT NUM FROM KOVAL.AUTO WHERE PERSONNEL_ID IN 
(SELECT ID FROM KOVAL.AUTO_PERSONNEL WHERE LAST_NAME = '������');
--2.	������� ������������ ������, � ������� ���� ���������� �������� � �������� ��������
SELECT NAME FROM KOVAL.ROUTES WHERE ID IN 
(SELECT ROUTE_ID FROM KOVAL.JOURNAL WHERE AUTO_ID IN 
(SELECT ID FROM KOVAL.AUTO WHERE PERSONNEL_ID IN 
(SELECT ID FROM KOVAL.AUTO_PERSONNEL WHERE LAST_NAME = '�������')));
--3.	������� ������������ ���������, �� ������� ���� �� ����������� ����������
SELECT NAME FROM KOVAL.ROUTES WHERE ID IN 
(SELECT ROUTE_ID FROM KOVAL.JOURNAL WHERE TIME_IN IS NULL);

--�	���������� ������ (join)
--1.	������ ������������ ��������� � ���� ��������/����������� �� ������� ���������
SELECT ROUTES.NAME, JOURNAL.TIME_OUT, JOURNAL.TIME_IN FROM KOVAL.JOURNAL JOIN KOVAL.ROUTES ON JOURNAL.ROUTE_ID = ROUTES.ID;
--2.	������� ����� �������� �� ������� ��������� � ������������ ���������, ������� ��������, �� ������� ������ �� ����
SELECT JOURNAL.TIME_OUT, ROUTES.NAME FROM KOVAL.JOURNAL RIGHT JOIN KOVAL.ROUTES ON JOURNAL.ROUTE_ID = ROUTES.ID;
--3.	������� ������� ��������/�������� �� ������� ���������, ������������ ��������� �������, ������� ��������, �� ������� �� ���� ������, � ������ �����������, ������� ���������� ������� �� ����������� � ������.
SELECT JOURNAL.TIME_OUT, JOURNAL.TIME_IN, ROUTES.NAME, AUTO.NUM
FROM KOVAL.JOURNAL 
FULL JOIN KOVAL.ROUTES ON JOURNAL.ROUTE_ID = ROUTES.ID
FULL JOIN KOVAL.AUTO ON JOURNAL.AUTO_ID = AUTO.ID
ORDER BY NAME, NUM;

--�	��� ���������� �������
--1.	������� ����� �������� ����� ������� �� ��������� ������������� �������� � ����� ����������, ������� �������� ������.
SELECT NUM, (TIME_IN-TIME_OUT) AS DELTA FROM KOVAL.JOURNAL JOIN KOVAL.AUTO ON JOURNAL.AUTO_ID = AUTO.ID
JOIN KOVAL.ROUTES ON JOURNAL.ROUTE_ID = ROUTES.ID 
WHERE (TIME_IN-TIME_OUT) = (SELECT MIN(TIME_IN-TIME_OUT) FROM KOVAL.JOURNAL) AND
NAME = '������������';

--2.	���������� ����������� ����������� � ����� �� ��������� ������������� ��������
SELECT COUNT(AUTO_ID) FROM KOVAL.JOURNAL JOIN KOVAL.ROUTES ON JOURNAL.ROUTE_ID = ROUTES.ID
WHERE NAME = '����������' AND TIME_IN IS NULL;

 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 ---   ������� ������
 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 --Hints: insert, where, in, exists, commit, rollback
 
--�	������������� �������
--1.	�������� ������ ��������
INSERT INTO KOVAL.AUTO_PERSONNEL (FIRST_NAME, LAST_NAME, FATHER_NAME) VALUES ('����','������','��������');

--2.	�������� �������� � �������� �������� ��� ����������
INSERT INTO KOVAL.AUTO (NUM, COLOR, MARK, PERSONNEL_ID) VALUES ('�567�','�������','����',(
    SELECT ID FROM KOVAL.AUTO_PERSONNEL WHERE LAST_NAME = '������'));
INSERT INTO KOVAL.AUTO (NUM, COLOR, MARK, PERSONNEL_ID) VALUES ('�767�','�������','��������',(
    SELECT ID FROM KOVAL.AUTO_PERSONNEL WHERE LAST_NAME = '������'));

--3.	��������� �������� � �������� �������� �� ������ ������ � ���� �� �������� � ��������� id.
INSERT INTO KOVAL.JOURNAL (TIME_OUT, AUTO_ID, ROUTE_ID) VALUES ('31-MAY-14 10.30.00.000000000 AM',(
    SELECT AUTO.ID FROM KOVAL.AUTO INNER JOIN KOVAL.AUTO_PERSONNEL ON AUTO.PERSONNEL_ID = AUTO_PERSONNEL.ID 
        WHERE(AUTO.COLOR LIKE '������' AND AUTO_PERSONNEL.LAST_NAME = '������')),'3');

--�	�������������� ������� � ������ ����������
--1.	�������� � ������ ���������� ��������, ���������� � ������ � ������ ��������� �� ������� � �����-���� ����
BEGIN
INSERT INTO KOVAL.AUTO_PERSONNEL (FIRST_NAME,LAST_NAME,FATHER_NAME) VALUES ('�������', '������', '����������');
INSERT INTO KOVAL.AUTO (NUM,COLOR,MARK,PERSONNEL_ID) VALUES ('H843TO','Black','Mercedes',(SELECT ID FROM (SELECT * FROM KOVAL.AUTO_PERSONNEL ORDER BY AUTO_PERSONNEL.ID DESC) WHERE ROWNUM=1));
INSERT INTO KOVAL.JOURNAL (TIME_OUT,AUTO_ID,ROUTE_ID) VALUES ('31-MAY-14 10.30.00.000000000 AM',(SELECT ID FROM (SELECT * FROM KOVAL.AUTO ORDER BY AUTO.ID DESC) WHERE ROWNUM=1),'4');
COMMIT;
END;

--2.	�������� ������ � ������, � ������, ���� ����������� � ������ ����� ������ 2-��, ���������� ��������
DECLARE auto_counter NUMBER;
BEGIN
INSERT INTO KOVAL.JOURNAL (TIME_OUT,AUTO_ID,ROUTE_ID) VALUES ((select to_char(sysdate) from dual),'1','1');
SELECT COUNT(AUTO_ID) INTO auto_counter FROM KOVAL.JOURNAL WHERE ROUTE_ID = 1;
IF (auto_counter > 2) THEN
    ROLLBACK;
ELSE
    COMMIT;
END IF;
END;

 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 ---   �������� ������
 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 --Hints: delete, where, in, exists, commit, rollback
 
--�	�������� �� ������� � �������� �� ��������� ������
--1.	������� ������� �� �������, ��� ���� �������� ������ ��������� ����
DELETE FROM KOVAL.JOURNAL WHERE TIME_IN < '27-MAR-15 08.26.39.080000000 AM';

--2.	������� ������� � ���, ��������� � ���, ������ � �������
DELETE FROM KOVAL.JOURNAL WHERE ROUTE_ID='6';
DELETE FROM KOVAL.ROUTES WHERE ID='6';

--3.	������� ��������, �� ������� �����������
DELETE FROM KOVAL.AUTO_PERSONNEL WHERE ID NOT IN (SELECT PERSONNEL_ID FROM KOVAL.AUTO);

--�	�������� � ������ ����������
--1.	������� � ������ ���������� ��������� �������� � ��� ����������
BEGIN
DELETE FROM KOVAL.AUTO_PERSONNEL WHERE ID='10';
DELETE FROM KOVAL.AUTO WHERE PERSONNEL_ID='10';
COMMIT;
END;

--2.	�� ��, ��� � �1, �� ���������� ��������
BEGIN
DELETE FROM KOVAL.AUTO_PERSONNEL WHERE ID='10';
DELETE FROM KOVAL.AUTO WHERE PERSONNEL_ID='10';
ROLLBACK;
END;

 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 ---   ����������� ������
 --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
 --Hints: update, where, in, exists, commit, rollback
 
--�	����������� �� �������
--1.	�������� ���� ���������� � �������� �������
UPDATE KOVAL.AUTO SET COLOR='�������' WHERE NUM='�567�';

--2.	�������� ����� �������� � �������� ������ ������� ���������
UPDATE KOVAL.JOURNAL SET TIME_IN=(select to_char(sysdate) from dual) WHERE ID='75';

--3.	�������� �������, �� �������� ������� ���������� � �������� �������
UPDATE KOVAL.JOURNAL SET ROUTE_ID = 1 WHERE AUTO_ID IN (
  SELECT ID FROM KOVAL.AUTO WHERE NUM = '�567�');

--�	����������� � ������ ����������
--1.	� ������ ���������� �������� �������� ������� ���� ������� � ������� �� ������ � ������� �������� �������.
BEGIN
UPDATE KOVAL.JOURNAL SET ROUTE_ID='4' WHERE ROUTE_ID='6';
DELETE FROM KOVAL.ROUTES WHERE ID='6';
COMMIT;
END;

--2.	�� ��, ��� � �1, �� ���������� ��������
BEGIN
UPDATE KOVAL.JOURNAL SET ROUTE_ID='4' WHERE ROUTE_ID='6';
DELETE FROM KOVAL.ROUTES WHERE ID='6';
ROLLBACK;
END;
