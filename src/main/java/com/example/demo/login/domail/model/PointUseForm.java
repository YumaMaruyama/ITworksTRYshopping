package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PointUseForm {

	
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$",groups = ValidGroup2.class)
	@Length(min=1,max=10,groups = ValidGroup3.class)
	private String pointUse;
}
