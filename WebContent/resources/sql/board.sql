use SaltLand;
create table noticeboard(
	num int not null auto_increment,
    id varchar(10) not null,
    name varchar(10) not null,
    category varchar(10) not null,
    title varchar(100) not null,
    content text not null,
    fileName varchar(30),
    regist_day varchar(30),
    ip varchar(20),
    primary key(num)
)default charset=utf8;

select * from noticeboard;
select * from noticeboard where id like '%001%';
select count(*) from noticeboard;
insert into noticeboard values( 1, 'admin', 'boardGM', 'notice','notice001','content001','event02.jpg','2021.10.22', '000.000.000.000'),
( 2, 'admin', 'boardGM', 'notice','notice002','content001','event02.jpg','2021.10.22', '000.000.000.000'),
( 3, 'admin', 'boardGM', 'notice','notice003','content001','event01.png','2021.10.22', '000.000.000.000'),
( 4, 'admin', 'boardGM', 'notice','notice004','content001','event02','2021.10.22', '000.000.000.000'),
( 5, 'admin', 'boardGM', 'event','HappyHolloween','It is holloween. Trick or treat!','event02.jpg','2021.10.22', '000.000.000.000'),
( 6, 'admin', 'boardGM', 'notice','notice005','content001','event01.png','2021.10.22', '000.000.000.000'),
( 7, 'admin', 'boardGM', 'notice','notice006','content001','event01.png','2021.10.22', '000.000.000.000'),
( 8, 'admin', 'boardGM', 'event','event002','content001','bg_pc_visual.png','2021.10.22', '000.000.000.000'),
( 9, 'admin', 'boardGM', 'event','event003','content001','event02.jpg','2021.10.22', '000.000.000.000'),
( 10, 'admin', 'boardGM', 'notice','notice007','content001','bg_pc_visual.png','2021.10.22', '000.000.000.000'),
( 11, 'admin', 'boardGM', 'notice','notice008','content001','event02.jpg','2021.10.22', '000.000.000.000'),
( 12, 'admin', 'Ghost', 'event','HappyHolloween','It is holloween. Trick or treat!','event03.jpg','2021.10.01', '000.000.000.000'),
( 13, 'admin', 'boardGM', 'notice','notice009','content001','event02.jpg','2021.10.22', '000.000.000.000');