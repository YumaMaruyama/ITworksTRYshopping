package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class LessonEndForm {

	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 8, max = 40, groups = ValidGroup2.class)
	private String password;
}
