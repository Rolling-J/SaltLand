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
select * from ticket where reserve_num = 130447;
select MAX(reserve_num) from ticket where id = 'testID';
select * from ticket where id = 'testID' order by reserve_num desc limit 1 ;
select * from ticket where id= 'testID' and reserve_time = '2021-10-15 14:15:00.000';
drop table ticket;
insert into ticket value(130447,'testID','2021/12/18',3,1,0,'37,000','2021-10-15 14:15:00.000');