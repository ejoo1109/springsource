create table bookTBL(
	code number(4) primary key,
	title nvarchar2(50) not null,
	writer nvarchar2(20)not null,
	price number(8) not null
);

insert into bookTBL values(1001,'이것이 자바다','신용권',28000);
insert into bookTBL values(1002,'재미있게 배우는 Oracle','박남정',29000);
insert into bookTBL values(1003,'Do it java','신영남',27000);
insert into bookTBL values(1004,'we can do it','김길동',32000);

select * from BOOKTBL;