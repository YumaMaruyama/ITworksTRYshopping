package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class InquiryForm {

	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 80, groups = ValidGroup2.class)
	private String title;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 200, groups = ValidGroup2.class)
	private String content;

}
