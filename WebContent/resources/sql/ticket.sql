use SaltLand;

create table ticket(
	reserve_num varchar(10)not null,
    id varchar(10) not null,    
    visit_date varchar(50),
    visit_type varchar(20),
    adult int,
    teenager int,
    children int,
    charge int,
    reserve_time varchar(50),
    primary key(reserve_num)
) default charset=utf8;

insert into ticket value('A0001','testID','2021/11/15','allday',3,1,0,37000,'2021-10-27 14:15:00.000');