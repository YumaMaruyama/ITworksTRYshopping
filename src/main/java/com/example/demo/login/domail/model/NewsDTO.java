package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class NewsDTO {

	private int id;
	
	private String title;
	
	private String content;
	
	private Date registrationDate;
}
