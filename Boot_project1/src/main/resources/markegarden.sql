-- 시퀀스
DROP SEQUENCE mg_seq_idx;
CREATE SEQUENCE mg_seq_idx;

DROP TABLE MAKETGARDEN;
-- 테이블 
-- 7/25 수정 초기 가입시 모두 rookie로 설정함 루키등급의 포인트값은 1000
-- 7/26 수정 주소 추가(주소찾기 기능 추가)
-- addr1은 우편번호 addr2는 주소 addr3은 상세주소(생략가능) 
-- addr4는 참고항목인데 이걸로 동네를 구분할꺼임
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
		addr4 varchar2(100),
		lev varchar2(100) DEFAULT 'rookie'
);

SELECT * FROM maketgarden;

-- 8/3 유동 게시판(로그인 없이 사용함)
-- 8/4 서머노트 사용을 위해 content varchar2 -> long 값으로 변경

-- 유동게시판 시퀀스
DROP SEQUENCE mgb_seq_idx;
CREATE SEQUENCE mgb_seq_idx;


CREATE TABLE mgboard(
	idx NUMBER PRIMARY KEY,
	name varchar2(200) NOT NULL,
	password varchar2(200) NOT NULL,
	subject varchar2(200) NOT NULL,
	content long NOT NULL,
	regdate timestamp DEFAULT sysdate,
	readcount NUMBER default 0,
	ip varchar2(200) NOT NULL
);


SELECT * FROM mgboard;

-- 8/4 우리동네 맛집 게시판 구현(유동닉)
-- 8/4 게시판 구현했는데 맛집을 표시할 주소가 필요함 
-- 주소 addr1 addr2을 넣어줌
-- 8/4 서머노트 사용을 위해 content varchar2 -> long 값으로 변경

DROP SEQUENCE mgm_seq_idx;
CREATE SEQUENCE mgm_seq_idx;

CREATE TABLE mgmboard(
	idx NUMBER PRIMARY KEY,
	name varchar2(200) NOT NULL,
	password varchar2(200) NOT NULL,
	subject varchar2(200) NOT NULL,
	content long NOT NULL,
	regdate timestamp DEFAULT sysdate,
	readcount NUMBER default 0,
	ip varchar2(200) NOT NULL,
	addr1 varchar2(100) NOT NULL,
	addr2 varchar2(100) NOT NULL
);
DROP TABLE mgmboard;
SELECT * FROM mgmboard;


-- 8/4 우리동네 핫딜 게시판 (유동닉)
-- 8/4 서머노트 사용을 위해 content varchar2 -> long 값으로 변경
DROP SEQUENCE mgh_seq_idx;
CREATE SEQUENCE mgh_seq_idx;
DROP TABLE mghboard;
CREATE TABLE mghboard(
	idx NUMBER PRIMARY KEY,
	name varchar2(200) NOT NULL,
	password varchar2(200) NOT NULL,
	subject varchar2(200) NOT NULL,
	content long NOT NULL,
	regdate timestamp DEFAULT sysdate,
	readcount NUMBER default 0,
	ip varchar2(200) NOT NULL,
	addr1 varchar2(100) NOT NULL,
	addr2 varchar2(100) NOT NULL
	);

SELECT * FROM mghboard;

-- 알바찾기 (고정닉 게시판으로 로그인시에만 사용할수 있는 게시판)
-- ref가 회원정보 idx를 ref랑 연결시키면 될꺼같은데?
-- 8/5 ref를 외래키로 연결해서 글 작성시 회원정보에서 읽어오도록함
DROP SEQUENCE mga_seq_idx;
CREATE SEQUENCE mga_seq_idx;
DROP TABLE mgaboard;
CREATE TABLE mgaboard(
	idx NUMBER PRIMARY KEY,
	REF NUMBER NOT NULL,
	subject varchar2(200) NOT NULL,
	content long NOT NULL,
	regdate timestamp DEFAULT sysdate,
	readcount NUMBER default 0,
	ip varchar2(200) NOT NULL,
	addr1 varchar2(100) NOT NULL,
	addr2 varchar2(100) NOT NULL,
	CONSTRAINT fk_ref FOREIGN KEY (ref) REFERENCES maketgarden(idx)
	);

SELECT * FROM mgaboard;

-- 외래키 삭제법
ALTER TABLE mgaboard
DROP CONSTRAINT fk_ref;

-- 중고거래 (고정닉 게시판으로 로그인시에만 사용할수 있는 게시판)
-- 8/6 거래추천기능 exchange 

DROP SEQUENCE mgu_seq_idx;
CREATE SEQUENCE mgu_seq_idx;
DROP TABLE mguboard;
CREATE TABLE mguboard(
	idx NUMBER PRIMARY KEY,
	REF NUMBER NOT NULL,
	name varchar2(200) NOT NULL,
	subject varchar2(200) NOT NULL,
	content long NOT NULL,
	regdate timestamp DEFAULT sysdate,
	readcount NUMBER default 0,
	ip varchar2(200) NOT NULL,
	addr1 varchar2(100) NOT NULL,
	addr2 varchar2(100) NOT NULL,
	exchange NUMBER DEFAULT 0,
	CONSTRAINT fk_ref1 FOREIGN KEY (ref) REFERENCES maketgarden(idx)
	);

SELECT * FROM mguboard;

-- 외래키 삭제법
ALTER TABLE mguboard
DROP CONSTRAINT fk_ref1;
