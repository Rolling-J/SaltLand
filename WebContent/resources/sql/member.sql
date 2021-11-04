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

insert into member value('admin','1234','manger','1970/10/27','female','HomepageManger@saltland.com','KT/010-1234-4321','2021-10-27 14:10:00.000'),
('testID','1234','noname1','1990/12/25','male','tester@daum.net','SK/010-4567-7654','2021-10-28 11:21:23.955'),
('testID2','1234','noname2','1990/04/20','male','testMail2@daum.net','KT/010-7894-4987','2021-10-29 12:20:25.22');

select * from member;