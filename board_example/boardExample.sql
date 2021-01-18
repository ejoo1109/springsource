create table board_example(
idx number(4),
	title varchar2(200) not null,
	content varchar2(200)not null,
	createdate date default sysdate,
	updatedate date default sysdate);

alter table board_example add constraint pk_board_example primary key(idx);

create sequence seq_board;
alter table board_example add createdate date default sysdate;
update board_example set idx='4'where idx='(4,0)';
select*from board_example;