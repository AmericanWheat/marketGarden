-- 시퀀스
DROP SEQUENCE mg_seq_idx;
CREATE SEQUENCE mg_seq_idx;

DROP TABLE MAKETGARDEN;
-- 테이블 
-- 7/25 수정 초기 가입시 모두 rookie로 설정함 루키등급의 포인트값은 1000
-- 7/26 수정 주소 추가(주소찾기 기능 추가)
-- addr1은 우편번호 addr2는 주소 addr3은 상세주소(생략가능)
-- 7/31 수정사항 비밀번호 암호화 인코딩시 문제로 인해 200자리로 늘림
-- lev의 글자를 100글자까지 허용
-- 관리자 lev의 변경 UPDATE MAKETGARDEN SET lev = 'ROLE_rookie,ROLE_admin' WHERE userid = 'admin';
CREATE TABLE maketgarden(
		idx NUMBER PRIMARY KEY,
		userid varchar2(20) NOT NULL,
		password varchar2(200) NOT NULL,
		name varchar2(20) NOT NULL,
		phone varchar2(20) NOT NULL,
		point number DEFAULT 1000,
		regdate timestamp DEFAULT sysdate,
		addr1 varchar2(100) NOT NULL,
		addr2 varchar2(100) NOT NULL,
		addr3 varchar2(100),
		lev varchar2(100) DEFAULT 'rookie'
)

SELECT * FROM maketgarden;

INSERT INTO MAKETGARDEN m values(
	mg_seq_idx.nextval, 'admin', 'admin999' , 'admin', '01011112222', '10000', sysdate ,'03048' ,'서울특별시 종로구 청와대로1', '본관', 'admin'
);

SELECT * FROM maketgarden;

