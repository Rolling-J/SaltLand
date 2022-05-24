use SaltLand;

create table ticket(
	reserve_num int auto_increment,
    id varchar(10) not null,    
    visit_date varchar(50),
    adult int,
    teenager int,
    children int,
    charge varchar(10),
    reserve_time varchar(50),
    primary key(reserve_num)
) default charset=utf8;

select * from ticket;
insert into ticket value(null,'testt','2022/5/30',3,1,0,'37000','2021-10-15 14:15:00.000');