SELECT sysdate FROM dual;

CREATE TABLE tbl_user(
	user_number NUMBER,
	user_id varchar2(10),
	user_pw varchar2(20),
	user_name varchar(100),
	user_age NUMBER,
	user_gender char(1),
	CONSTRAINT pk_user PRIMARY KEY(user_number)
);

CREATE SEQUENCE seq_user;

SELECT * FROM tbl_user;
DELETE FROM TBL_USER WHERE USER_NUMBER = 3;


SELECT * FROM TBL_USER tu WHERE USER_ID = 'test' AND USER_PW = '123';

DROP TABLE tbl_user;

DROP SEQUENCE seq_user;


CREATE TABLE tbl_member(
	member_number NUMBER,
	member_id varchar2(300),
	member_password varchar2(300),
	member_name varchar2(300),
	member_age number(3),
	member_gender char(1),
	CONSTRAINT pk_member PRIMARY KEY(member_number)
);

SELECT * FROM tbl_member;

-- 사이클과 캐시를 없애고 속성 부여- 시퀀스 테이블
CREATE SEQUENCE seq_member START WITH 1 INCREMENT BY 1
nocache    -- 시퀀스 값을 미리 메모리에 캐싱하지 않도록 설정(고유번호가 끊기지 않아야하는 경우 사용-데이터 연속성이 중요할 때)  
nocycle;   -- 최대값에 도달하면 재시작하지 않도록 설정



