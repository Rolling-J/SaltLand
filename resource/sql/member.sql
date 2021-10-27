create database SaltLand;
use SaltLand;

create table member(
	id varchar(10) not null,
    password varchar(10) not null,
    name varchar(10) not null,
    birth varchar(10),
    gender varchar(6),
    mail varchar(30),
    phone varchar(20),
    regist_day varchar(50),
    primary key(id)
) default charset=utf8;

select * from member;