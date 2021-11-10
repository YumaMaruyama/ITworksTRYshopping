package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChallengeProgrammingEvaluationDTO {

	private int id;
	
	private int teacherId;
	
	private int userId;
	
	private String evaluationDetail;
	
	private Date registrationDate;
}
