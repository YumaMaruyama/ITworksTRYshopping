package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;
@Data
public class ChallengeProgrammingContractDTO {

	private int id;
	
	private int userId;
	
	private String phoneNumber;
	
	private String mailAddress;
	
	private Date contractDate;
}
