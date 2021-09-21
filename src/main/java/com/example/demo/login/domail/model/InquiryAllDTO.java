package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryAllDTO {

	private int id;

	private String title;

	private String content;

	private Date registrationDate;

	private int adminId;

	private int inquiryId;

	private String adminTitle;

	private String adminContent;

	private Date adminRegistrationDate;

}
