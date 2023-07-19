create database db_car_rental;
use db_car_rental;
create table branches(
                         id bigint primary key not null auto_increment,
                         name varchar(50),
                         address varchar(200)
);
create table customers(
                          id bigint primary key not null auto_increment,
                          name varchar(50),
                          license varchar(10),
                          contact varchar(14),
                          email varchar(25)
);
create table cars(
                     id bigint primary key not null auto_increment,
                     make varchar(20),
                     model varchar(20),
                     year varchar(10),
                     rate double,
                     availability boolean
);
create table bookings(
                         id bigint primary key not null auto_increment,
                         booked_on date,
                         days_booked int,
                         customer_id bigint,
                         foreign key (customer_id) references customers(id),
                         car_id bigint,
                         foreign key (car_id) references cars(id),
                         pickup varchar(30),
                         destination varchar(30),
                         charge long
);

create table users(
                      id bigint primary key not null auto_increment,
                      name varchar(25),
                      password varchar(25),
                      branch_id bigint,
                      foreign key(branch_id) references branches(id),
                      role enum('ROLE_USER','ROLE_ADMIN')
);