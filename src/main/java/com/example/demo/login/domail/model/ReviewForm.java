package com.example.demo.login.domail.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ReviewForm {

	private int id;
	
	private int userId;
	
	private int productId;
	
	private String rating;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 40, groups = ValidGroup2.class)
	private String title;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 200, groups = ValidGroup2.class)
	private String content;
	
	private Date registrationDate;
}
