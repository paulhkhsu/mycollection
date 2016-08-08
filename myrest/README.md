Example of Spring Data JPA application (Hibernate used as implementation of JPA).

Demo for both rest and MVC
http://localhost:8080/myrest/ for mvc web page


CREATE TABLE `employee` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `employees_number` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
