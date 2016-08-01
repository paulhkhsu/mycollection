create table book(
      id int not null primary key,
      name varchar_ignorecase(50) not null,
      author varchar_ignorecase(50) not null,
      price int);
 use test;
create table book(
      id int not null AUTO_INCREMENT,
      name varchar(50) not null,
      author varchar(50) not null,
      price int,
       PRIMARY KEY ( id )
       );
