Example of Spring Data JPA application (Hibernate used as implementation of JPA).


CREATE TABLE `employee` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `employees_number` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
