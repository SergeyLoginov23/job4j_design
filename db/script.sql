create table cars (
	id 	serial primary key,
	mark varchar(200),
	age integer,
	color varchar(200)
);

insert into cars(mark, age, color) values('Ford', 2005, 'Blue');

update cars SET color = 'Red';

delete from cars;
