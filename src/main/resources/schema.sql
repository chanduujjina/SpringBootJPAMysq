DROP TABLE IF EXISTS Employees;

DROP TABLE IF EXISTS Employee;
  
CREATE TABLE Employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
   age INT,
   address VARCHAR(250),
   salary INT,
   departmentName VARCHAR(250)
  
);