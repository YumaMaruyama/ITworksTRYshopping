package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChallengeProgrammingDTO {

	private int id;
	
	private String title;
	
	private String content;
	
	private int price;
	
	private int workingTimes;
	
	private Date fixableTimeFrom;//勤務可能時間
	
	private Date fixableTimeTo;
	
	private String img;
	
	private String fixableTimeFromGetTime;
	
	private String fixableTimeToGetTime;
}
