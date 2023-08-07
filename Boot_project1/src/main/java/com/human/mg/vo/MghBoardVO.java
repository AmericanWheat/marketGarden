package com.human.mg.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MghBoardVO {
	
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date regDate;
	private int readCount;
	private String ip;
	private String addr1;
	private String addr2;
		
}
