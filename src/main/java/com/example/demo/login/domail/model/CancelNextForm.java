package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CancelNextForm {

	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 300, groups = ValidGroup2.class)
	private String content;
	
}
