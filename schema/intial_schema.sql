create database db_car_rental;
use db_car_rental;
create table branch(
                       id int primary key not null auto_increment,
                       location varchar(50)
);
create table customer(
                         id int primary key not null auto_increment,
                         name varchar(50),
                         contact varchar(14),
                         email varchar(25)
);
create table car(
                    id int primary key not null auto_increment,
                    make varchar(20),
                    model varchar(20),
                    rate double,
                    availability boolean
);
create table booking(
                        id int primary key not null auto_increment,
                        booked_on date,
                        days_booked int,
                        customer_id int,
                        foreign key (customer_id) references customer(id),
                        car_id int,
                        foreign key (car_id) references car(id),
                        pickup varchar(30),
                        destination varchar(30),
                        charge int
);

create table users(
                      id int primary key not null auto_increment,
                      name varchar(25),
                      password varchar(25),
                      branch int,
                      foreign key(branch) references branch(id),
                      role enum('ROLE_USER','ROLE_ADMIN')
);

