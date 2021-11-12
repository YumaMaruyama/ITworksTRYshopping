package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChallengeProgrammingHistoryDTO {

	private int id;
	
	private int userId;
	
	private int price;
	
	private String teacherName;
	
	private String title;
	
	private String content;
	
	private Date startDate;
	
	private Date endDate;
}
