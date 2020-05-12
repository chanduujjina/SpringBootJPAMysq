"# SpringBootJPAMysq" 

1.Spring boot jpa with mysql

2.Spring boot rest end point exposured

3.Spring boot swagerre
4.Jpa specification
5.MapStruct conecepsts
6.Swagger implementation


scheama name :test_db

CREATE TABLE `employee` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `TYPE_ID` int(9) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DEPT_ID` int(9) DEFAULT NULL,
  `SALARY` int(20) DEFAULT NULL,
  `EXPERIENCE` int(5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8


CREATE TABLE `address_details` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `emp_id` int(20) NOT NULL,
  `address_type` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `address_details_ibfk_1` (`emp_id`),
  CONSTRAINT `address_details_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8


CREATE TABLE `dept_details` (
  `ID` int(9) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(15) NOT NULL,
  `LOCATION` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8


CREATE TABLE `employee_type` (
  `ID` int(9) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8



CREATE TABLE `skill_set` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `emp_id` int(20) NOT NULL,
  `skill_type` varchar(20) DEFAULT NULL,
  `skill_name` varchar(20) DEFAULT NULL,
  `relevent_exp` int(5) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `skill_set_ibfk_1` (`emp_id`),
  CONSTRAINT `skill_set_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8

