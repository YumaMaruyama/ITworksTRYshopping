package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CancelForm {

	private String title;
	
	private String content;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 7, max = 7, groups = ValidGroup2.class)
	private String bankNumber;
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 3, groups = ValidGroup2.class)
	private String storeName;
}
