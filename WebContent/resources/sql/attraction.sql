use salt;
CREATE TABLE IF NOT EXISTS attraction(
	name varchar(10) not null,
    info varchar(150) not null,
    tag char(4) not null,
    ride varchar(5),
    age varchar(10),
    tall varchar(15),
    filename varchar(20),
    primary key(name)    
)default charset=utf8;
select*from attraction;