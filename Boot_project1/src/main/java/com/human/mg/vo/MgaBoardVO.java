package com.human.mg.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MgaBoardVO {
	
	private int idx;
	private int ref; //회원 정보테이블의 idx를 가져옴
	private String name; //이름이 있어야 리스트 보여줄때 보여줌
	private String subject;
	private String content;
	private Date regDate;
	private int readCount;
	private String ip;
	private String addr1;
	private String addr2;
		
}
