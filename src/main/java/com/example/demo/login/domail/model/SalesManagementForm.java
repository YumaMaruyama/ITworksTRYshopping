package com.example.demo.login.domail.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SalesManagementForm {

	@Length(max = 20,groups = ValidGroup1.class)
	private String purchaseName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDateFrom;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDateTo;
}
