package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class LessonEvaluationForm {

	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 100, groups = ValidGroup2.class)
	private String evaluatonDetail;
	
	private String rating;
}
