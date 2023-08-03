package com.human.mg.vo;

import java.util.Date;

import lombok.Data;

/*
 * CREATE TABLE mgboard(
	idx NUMBER PRIMARY KEY,
	name varchar2(200) NOT NULL,
	password varchar2(200) NOT NULL,
	subject varchar2(200) NOT NULL,
	content varchar2(2000) NOT NULL,
	regdate timestamp DEFAULT sysdate,
	readcount NUMBER default 0,
	ip varchar2(200) NOT NULL
);
 * */


@Data
public class MgBoardVO {
	
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date regDate;
	private int readCount;
	private String ip;
	
}
