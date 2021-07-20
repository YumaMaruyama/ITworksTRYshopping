package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryDTO {

	
	private int id;
	
	private int user_id;
	
	private String title;
	
	private String content;
	
	private Date registrationDate;
	
	private Date finishedDate;
}
