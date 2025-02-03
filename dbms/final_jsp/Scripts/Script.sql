SELECT sysdate FROM dual;

DROP TABLE tbl_member;
DROP TABLE TBL_BOARD ;
DROP TABLE TBL_FILE ;

-- 시퀀스 테이블 삭제 
DROP  SEQUENCE seq_member;
DROP  SEQUENCE seq_board;

-- 1. member 시퀀스 생성
CREATE SEQUENCE seq_member START with 1 INCREMENT BY 1
nocache
nocycle;

-- 2. member 테이블 생성
CREATE TABLE tbl_member(
		member_number NUMBER,
		member_id varchar2(300),
		member_password varchar2(300),
		member_name varchar2(300),
   		member_phone_number varchar2(300),
		member_age NUMBER(3),
		member_gender char(1),
		CONSTRAINT pk_member PRIMARY KEY(member_number)
	);


-- board 테이블 생성

CREATE table tbl_board(
	board_number NUMBER,
	board_title varchar2(300),
	board_content varchar2(1000),
	board_date timestamp DEFAULT CURRENT_timestamp, -- 현재날짜 / 시간 기본값
	member_number NUMBER, --fk 참조값
	CONSTRAINT pk_board PRIMARY KEY (board_number),
	CONSTRAINT fk_board FOREIGN KEY (member_number) references tbl_member(member_number) ON DELETE cascade
);

-- board 시퀀스 생성
CREATE SEQUENCE seq_board START WITH 1 INCREMENT BY 1
nocache
nocycle;

SELECT * FROM tbl_board;
SELECT * FROM tbl_member;

UPDATE TBL_MEMBER A
SET A.MEMBER_PASSWORD = '1234' -- {#입력받을 값}
WHERE 

컨트로안에서 
