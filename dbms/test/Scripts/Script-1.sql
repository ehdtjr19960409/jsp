SELECT * FROM tbl_user;

SELECT  * FROM TBL_STUDY ts ;

ALTER TABLE TBL_USER MODIFY USER_BIRTH VARCHAR2(30);


		SELECT USER_ID, USER_PH 
		FROM TBL_USER
		WHERE USER_PH ='051-624-9990'  AND USER_ID = 'yno';
		
--락 걸린 테이블 확인하기	 ORA-00060
--SELECT DISTINCT object_id 
--FROM v$locked_object 
--WHERE oracle_username = 'note';
--
-- 락 걸린 테이블 갯수 확인
--SELECT COUNT(*) 
--FROM v$locked_object vo, dba_objects do 
--WHERE vo.object_id = do.object_id;
--
-- 락 걸린 세션 확인
--SELECT a.sid, a.serial#
--FROM v$session a, v$lock b, dba_objects c
--WHERE a.sid = b.sid
--  AND b.id1 = c.object_id
--  AND b.type = 'TM';
--
-- 락 걸린 세션 죽이는 방법
--ALTER SYSTEM KILL SESSION 'sid,serial#';

-- 참고 사이트 : https://gist.github.com/jacknie84/bb9474742eb1b5b8e11c43036dc485f8

-- ora-01841 // user_birth 컬럼에 대한
--	 데이터의 크기가 오라클의 MAX값을 초과한 경우
--  date의 max값인 '99991231' 이상의 일자를 등록하려 하는 경우

-- ora-01861
-- 리터럴 타입이 맞지 않는 현상
-- DB 타입은 DATE에 8자리 이지만

-- 문제 확인
-- Java 타입은 String 타입에 6자리인 걸로 확인
-- mapper에서 db를 TO_DATE(#{userBirth}, 'YYYY-MM-DD') 형식으로 변경

 
-- ora-00913
-- 해당 컬럼의 값을 삽입하는 과정에서 개수가 일치하지 않는 현상
-- 컬럼의 개수 혹은 값의 개수를 확인 후 쿼리문 재작성을 통해 해당 오류 해결