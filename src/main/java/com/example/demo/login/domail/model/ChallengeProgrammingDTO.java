package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChallengeProgrammingDTO {

	private int id;
	
	private String title;
	
	private String content;
	
	private int price;
	
	private String workingTimes;
	
	private Date fixable_timeFrom;//勤務可能時間
	
	private Date fixable_timeTo;
}
