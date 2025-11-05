
create table 고객 (
    고객아이디 varchar(20) not null primary key,
    고객이름 varchar(10) not null,
    나이 INT,
    등급 varchar(10) not null,
    직업 varchar(20),
    적립금 INT default 0
);

create table 제품(
    제품번호 varchar(5) not null,
    제품명 varchar(20),
    재고량 int,
    단가 int,
    제조업체 varchar(20),
    primary key (제품번호),
    check(재고량>=0 and 재고량<=10000)
);

create table 주문(
    주문번호 varchar(5) not null,
    주문고객 varchar(20),
    주문제품 varchar(5),
    수량 int,
    배송지 varchar(30),
    주문일자 date,
    primary key (주문번호),
    foreign key (주문고객) references 고객(고객아이디),
    foreign key (주문제품) references 제품(제품번호)
);

create table 배송업체(
    업체번호 varchar(5) not null primary key,
    업체명 varchar(20),
    주소 varchar(100),
    전화번호 varchar(20)
)

--테이블 변경
alter table 고객
                add 가입날짜 data;      
alter table 고객
    drop column 가입날짜;

-- 테이블 변경: 체크 제약조건 추가
alter table 고객
        add constraint check_age check(나이>=20);
      
-- 테이블 변경: 체크 제약조건 삭제
alter table 고객
        drop constraint check_age;
        
--테이블 삭제
drop table 배송업체;

--고객 테이블에 행 삽입(추가)
insert into 고객 values ('apple', '정소화', 20, 'gold', '학생', 1000);
insert into 고객 values ('banana', '정소화', 20, 'gold', '학생', 1000);
insert into 고객 values ('carrot', '정소화', 20, 'gold', '학생', 1000);
insert into 고객 values ('orange', '정소화', 20, 'gold', '학생', 1000);
insert into 고객 values ('', '정소화', 20, 'gold', '학생', 1000);
insert into 고객 values ('', '정소화', 20, 'gold', '학생', 1000);
insert into 고객 values ('', '정소화', 20, 'gold', '학생', 1000);

select * from 고객;




