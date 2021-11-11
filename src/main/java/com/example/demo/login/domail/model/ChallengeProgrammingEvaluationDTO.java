package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChallengeProgrammingEvaluationDTO {

	private int id;
	
	private int teacherId;
	
	private String teacherName;
	
	private int userId;
	
	private String userName;
	
	private int rate;
	
	private String evaluationDetail;
	
	private Date registrationDate;
}
