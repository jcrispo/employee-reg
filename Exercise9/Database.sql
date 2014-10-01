CREATE DATABASE EXERCISE9;
USE EXERCISE9;

CREATE TABLE departments (
    id INT NOT NULL AUTO_INCREMENT,
    department VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE positions (
    id INT NOT NULL AUTO_INCREMENT,
    position VARCHAR(20) NOT NULL UNIQUE,
    departmentId INT,
    PRIMARY KEY (id),
    FOREIGN KEY (departmentId) REFERENCES departments(id)
) ENGINE=InnoDB;
CREATE TABLE person (
    id INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(20) NOT NULL,
    middleName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    gender VARCHAR(7),
    birthDate DATE,
    PRIMARY KEY (id),
    UNIQUE (firstName, middleName, lastName, birthDate)
) ENGINE=InnoDB;
CREATE TABLE companyEmployeeData (
    id INT NOT NULL AUTO_INCREMENT,
    positionId INT NOT NULL,
    hireDate DATE NOT NULL,
    salary DECIMAL(10,2),
    email VARCHAR(50) UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES person(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (positionId) REFERENCES positions(id)
) ENGINE=InnoDB;
SHOW TABLES;

