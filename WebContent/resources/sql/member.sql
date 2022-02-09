CREATE DATABASE SaltLand DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use SaltLand;
drop database SaltLand;
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

insert into member value('testID','1234','noname1','1990/12/25','male','tester@daum.net','010-4567-7654','2021-10-28 11:21:23.955'),
('testID2','1234','noname2','1990/04/20','male','testMail2@daum.net','010-7894-4987','2021-10-29 12:20:25.22');

select * from member;
drop table member;
