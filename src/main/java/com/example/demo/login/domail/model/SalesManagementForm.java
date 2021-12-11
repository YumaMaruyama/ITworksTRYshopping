package com.example.demo.login.domail.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SalesManagementForm {

	private String purchaseName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDateFrom;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDateTo;
}
