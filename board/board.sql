create table spring_board(
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
);

alter table spring_board add constraint pk_spring_board primary key(bno);

create sequence seq_board;

select*from spring_board;

insert into spring_board(bno,title,content,writer) 
values(seq_board.nextval,'자바','자바','자바');

-------------------------------------------------------------
-- page(페이지) 나누기 : rownum (데이터의 순번 매기기)
select rownum, bno, title from spring_board where rownum <=10;

--방법1.가장 최신글 10개 가져오기 (bno 기준으로 조회한걸 다시 rownum 순번 적용)
select rownum, bno, title
from(select bno, title from spring_board order by bno desc)
where rownum<=10;

--방법2.가장 최신글 10개 가져오기 (bno 기준으로 조회한걸 다시 rownum 순번 적용)
select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum, bno, title
from spring_board
where rownum<=10;

--방법 1. 1page 페이지에서 보여지는-> 가장 최신글 10개 (0~10번게시글)
select rn, bno, title
from (select rownum rn, bno, title
		from(select bno, title from spring_board order by bno desc)
		where rownum<=10)
where rn>0;

--방법 2. 1page 페이지에서 보여지는 -> 가장 최신글 10개 (0~10번게시글)
select rn, bno, title
from(select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum rn, bno, title
	from spring_board
	where rownum<=10)
where rn>0;

-- 방법1. 2page 페이지에서 보여지는 -> 그다음 최신글 10개 (11~20번 게시글)
select rn, bno, title
from (select rownum rn, bno, title
		from(select bno, title from spring_board order by bno desc)
		where rownum<=20)
where rn>10;

-- 방법2. 2page 페이지에서 보여지는 -> 그다음 최신글 10개 (11~20번 게시글)
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board)*/
	rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where rownum<=20)
where rn>10;

-- page 번호가 넘어가면서 게시글 개수를 가져올때(rownum<=?) ?에 해당하는 조건식
-- 1페이지를 누르면->0(page번호-1)*현재 페이지의 보여줄 게시물 개수,10(page번호 * 현재 페이지의 보여줄 게시물 개수)
-- 만약 1페이지를 눌렀을때 1~10개를 보여주고 싶다면 (1-1)*10, 1*10의 조건식을 사용함
-- 2페이지를 누르면-> 11~20개를 보여주고 싶다면 (2-1)*10, 2*10의 조건식
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board)*/
	rownum rn, bno, title, writer, regdate, updatedate
	from spring_board
	where rownum<=?)
where rn>?;

--더미 데이터 삽입
insert into spring_board(bno,title, content, writer)
(select seq_board.nextval,title, content, writer from spring_board);

select count(*) from spring_board;

--검색(제목에 제목이라는 글자가 포함)
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board)*/
	rownum rn, bno, title, writer, regdate, updatedate
	from spring_board where(title like '%제목%') and rownum<=10)
where rn>0;

--검색(title과 content)
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board)*/
	rownum rn, bno, title, writer, regdate, updatedate
	from spring_board where (title like '%제목%' 
	or content like '%내용%')and rownum<=10)
where rn>0;

--검색(title, content, writer)
select *
from(select /*+INDEX_DESC(spring_board pk_spring_board)*/
	rownum rn, bno, title, writer, regdate, updatedate
	from spring_board where (title like '%제목%' or content like '%내용%' 
	or writer like '홍길동') and rownum<=10)
where rn>0;

--댓글 테이블
create table spring_reply(
	rno number(10,0) constraint pk_reply primary key, --댓글 글번호
	bno number(10,0) not null, --원본글 글번호
	reply varchar2(1000) not null, --댓글
	replyer varchar2(50) not null, --댓글 작성자
	replyDate date default sysdate, -- 댓글 작성일
	updateDate date default sysdate, --댓글 수정일
	constraint fk_reply_board foreign key(bno) references spring_board(bno) --외래키 설정
)

create sequence seq_reply;

--인덱스 생성
create index idx_reply on spring_reply(bno desc,rno asc);


select * from spring_reply;