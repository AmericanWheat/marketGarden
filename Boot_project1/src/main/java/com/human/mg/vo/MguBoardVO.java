package com.human.mg.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MguBoardVO {
	
	private int idx;
	private int ref; //회원 정보테이블의 idx를 가져옴
	private String name; 
	private String subject;
	private String content;
	private Date regDate;
	private int readCount;
	private String ip;
	private String addr1;
	private String addr2;
	private int exchange; //글의 거래 추천수

		
}
