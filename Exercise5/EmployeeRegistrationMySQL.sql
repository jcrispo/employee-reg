CREATE DATABASE Employee_Registration;
USE Employee_Registration;

CREATE TABLE departments (
    deptId INT NOT NULL AUTO_INCREMENT,
    dept_name VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (deptId)
) ENGINE=InnoDB;
CREATE TABLE employeePosition (
    position_refId INT NOT NULL AUTO_INCREMENT,
    position_name VARCHAR(20) NOT NULL UNIQUE,
    deptId INT,
    PRIMARY KEY (position_refId),
    FOREIGN KEY (deptId) REFERENCES departments(deptId)
) ENGINE=InnoDB;
CREATE TABLE personalInfo (
    employeeId INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(20) NOT NULL,
    middleName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    gender VARCHAR(7),
    birthDate DATE,
    PRIMARY KEY (employeeId),
    UNIQUE (firstName, middleName, lastName, birthDate)
) ENGINE=InnoDB;
CREATE TABLE companyEmployeeData (
    employeeId INT NOT NULL AUTO_INCREMENT,
    position_refId INT NOT NULL,
    hireDate DATE NOT NULL,
    basicSalary DECIMAL(10,2),
    emailId VARCHAR(20) UNIQUE,
    PRIMARY KEY (employeeId),
    FOREIGN KEY (employeeId) REFERENCES personalInfo(employeeId) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;
SHOW TABLES;
INSERT INTO departments (dept_name) VALUES ('accounting');
INSERT INTO departments (dept_name) VALUES ('public relations');
INSERT INTO departments (dept_name) VALUES ('development');
INSERT INTO departments (dept_name) VALUES ('administrative');
INSERT INTO departments (dept_name) VALUES ('maintenance');
INSERT INTO departments (dept_name) VALUES ('other');
SELECT * FROM departments;
INSERT INTO employeePosition (position_name, deptId) VALUES ('accountant', 1);
INSERT INTO employeePosition (position_name, deptId) VALUES ('bookkeeper', 1);
INSERT INTO employeePosition (position_name, deptId) VALUES ('controller', 1);
INSERT INTO employeePosition (position_name, deptId) VALUES ('receptionist', 2);
INSERT INTO employeePosition (position_name, deptId) VALUES ('staff', 2);
INSERT INTO employeePosition (position_name, deptId) VALUES ('secretary', 2);
INSERT INTO employeePosition (position_name, deptId) VALUES ('java developer', 3);
INSERT INTO employeePosition (position_name, deptId) VALUES ('software tester', 3);
INSERT INTO employeePosition (position_name, deptId) VALUES ('qa engineer', 3);
INSERT INTO employeePosition (position_name, deptId) VALUES ('project manager', 4);
INSERT INTO employeePosition (position_name, deptId) VALUES ('cfo', 4);
INSERT INTO employeePosition (position_name, deptId) VALUES ('ceo', 4);
INSERT INTO employeePosition (position_name) VALUES ('janitor');
SELECT * FROM employeePosition;
/*
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('jason ray','ibanez','crispo','male','1988-07-04');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('robert','james','abbatacola','male','1984-05-13');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('joseph','kurt','burton','male','1975-09-01');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('caitlin','johnson','alford','female','1980-01-29');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('frieda','steitz','millhouse','female','1985-04-07');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('irina','hyden','schutz','female','1977-08-25');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('jana','gribble','paden','female','1964-07-17');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('alfredo','brucker','strub','male','1990-03-11');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('natisha','zerr','juan','female','1983-12-03');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('dominic','corle','sander','male','1970-07-06');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('kimberlie','doud','suggs','female','1978-10-23');
INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES ('alfonse','edward','ulrich','male','1988-06-04');
SELECT * FROM personalInfo;
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (7,'2014-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (3,'2014-11-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (12,'2005-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (4,'2014-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (8,'2014-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (2,'2014-10-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (7,'2014-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (11,'2005-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (4,'2014-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (5,'2014-12-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (1,'2014-07-04');
INSERT INTO companyEmployeeData (position_refId, hireDate) VALUES (9,'2014-07-04');
SELECT * FROM companyEmployeeData;


SELECT e.employeeId, e.firstName, e.middleName, e.lastName, e.gender, e.birthDate, e.position_name, d.dept_name, e.hireDate, e.basicSalary, e.emailId FROM departments AS d RIGHT JOIN (SELECT empData.employeeId, empData.emailId, empData.firstName, empData.middleName, empData.lastName, empData.gender, empData.birthDate, empData.hireDate, empData.basicSalary, pos.position_name, pos.deptId FROM employeePosition AS pos RIGHT JOIN (SELECT empd.employeeId, empd.firstName, empd.middleName, empd.lastName, empd.birthDate, empd.gender, companyEmployeeData.hireDate, companyEmployeeData.basicSalary, companyEmployeeData.emailId, companyEmployeeData.position_refId FROM companyEmployeeData RIGHT JOIN (SELECT emp.employeeId, emp.firstName, emp.middleName, emp.lastName, emp.birthDate, emp.gender FROM personalInfo AS emp ORDER BY emp.employeeID LIMIT 0, 100) AS empd ON companyEmployeeData.employeeId = empd.employeeId) AS empData ON empData.position_refId = pos.position_refId) AS e ON e.deptId = d.deptId

SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate, personalInfo.gender, company.hireDate, company.position_name, company.dept_name, company.basicSalary, company.emailId FROM personalInfo LEFT JOIN (SELECT companyEmployeeData.employeeId, companyEmployeeData.hireDate, posdept.position_refId, posdept.position_name,  posdept.dept_name, companyEmployeeData.basicSalary, companyEmployeeData.emailId FROM companyEmployeeData LEFT JOIN (SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition LEFT JOIN departments ON employeePosition.deptId=departments.deptId) AS posdept ON companyEmployeeData.position_refId=posdept.position_refId) AS company ON personalInfo.employeeId=company.employeeId ORDER BY personalInfo.employeeId;

SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition LEFT JOIN departments ON employeePosition.deptId = departments.deptId;

SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate, personalInfo.gender, company.hireDate, company.position_name, company.dept_name, company.basicSalary, company.emailId FROM personalInfo 
    LEFT JOIN (SELECT companyEmployeeData.employeeId, companyEmployeeData.hireDate, posdept.position_refId, posdept.position_name,  posdept.dept_name, companyEmployeeData.basicSalary, companyEmployeeData.emailId FROM companyEmployeeData 
        LEFT JOIN (SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition 
            LEFT JOIN departments 
            ON employeePosition.deptId=departments.deptId) 
        AS posdept ON companyEmployeeData.position_refId=posdept.position_refId) 
    AS company ON personalInfo.employeeId=company.employeeId ORDER BY personalInfo.employeeId LIMIT 0, 1000;


SELECT * FROM employeePosition INNER JOIN departments ON employeePosition.deptId=departments.deptId ORDER BY employeePosition.position_refId;
SELECT * FROM employeePosition RIGHT JOIN departments ON employeePosition.deptId=departments.deptId ORDER BY employeePosition.position_refId;
SELECT * FROM employeePosition LEFT JOIN departments ON employeePosition.deptId=departments.deptId ORDER BY employeePosition.position_refId;
SELECT * FROM (
    SELECT employeePosition.position_refId AS id1, employeePosition.position_name AS pName1, employeePosition.deptId AS pdId1, departments.deptId AS dId1, departments.dept_name AS dName1
        FROM employeePosition LEFT JOIN departments ON employeePosition.deptId=departments.deptId
    UNION
    SELECT employeePosition.position_refId AS id, employeePosition.position_name AS pName, employeePosition.deptId AS pdId, departments.deptId AS dId, departments.dept_name AS dName
        FROM employeePosition RIGHT JOIN departments ON employeePosition.deptId=departments.deptId
    ) emp
    ORDER BY emp.id1;
SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate, personalInfo.gender, companyEmployeeData.hireDate, employeePosition.position_name, departments.dept_name
FROM personalInfo, employeePosition, companyEmployeeData, departments  
WHERE personalInfo.employeeId=companyEmployeeData.employeeId AND employeePosition.position_refId=companyEmployeeData.position_refId AND employeePosition.deptId=departments.deptId
ORDER BY personalInfo.employeeId ASC;


departments
+--------+-----------------------+
| deptId | dept_name             |
+--------+-----------------------+
|      1 | accounting department |
|      2 | public relations      |
|      3 | development           |
|      4 | administrative        |
+--------+-----------------------+
4 rows in set (0.00 sec)

employeePositions
+----------------+-----------------+--------+
| position_refId | position_name   | deptId |
+----------------+-----------------+--------+
|              1 | accountant      |      1 |
|              2 | bookkeeper      |      1 |
|              3 | controller      |      1 |
|              4 | receptionist    |      2 |
|              5 | staff           |      2 |
|              6 | secretary       |      2 |
|              7 | java developer  |      3 |
|              8 | software tester |      3 |
|              9 | qa engineer     |      3 |
|             10 | project manager |      4 |
|             11 | cfo             |      4 |
|             12 | ceo             |      4 |
+----------------+-----------------+--------+
12 rows in set (0.00 sec)


SELECT employeePosition.position_name, dept_name FROM employeePosition, departments WHERE employeePosition.deptId = departments.deptId ORDER BY position_name ASC;
+-----------------+-----------------------+
| position_name   | dept_name             |
+-----------------+-----------------------+
| accountant      | accounting department |
| bookkeeper      | accounting department |
| ceo             | administrative        |
| cfo             | administrative        |
| controller      | accounting department |
| java developer  | development           |
| project manager | administrative        |
| qa engineer     | development           |
| receptionist    | public relations      |
| secretary       | public relations      |
| software tester | development           |
| staff           | public relations      |
+-----------------+-----------------------+
12 rows in set (0.00 sec)



*/


