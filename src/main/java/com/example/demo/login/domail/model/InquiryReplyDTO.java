package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryReplyDTO {

	private int id;

	private int inquiryId;

	private String title;

	private String content;

	private Date RegistrationDate;
	
}
