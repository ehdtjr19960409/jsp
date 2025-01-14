SELECT * FROM TBL_MEMBER;

INSERT INTO tbl_member VALUES (seq_member.nextval, '짱구');
 
DROP SEQUENCE seq_member;
DROP table tbl_member ;

create table tbl_member(member_id number, member_name varchar2(100));
create sequence seq_member;

