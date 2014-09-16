CREATE DATABASE COMPANY_DB;
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
