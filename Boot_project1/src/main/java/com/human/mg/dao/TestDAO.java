package com.human.mg.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {

	String selectToday();
		
}
