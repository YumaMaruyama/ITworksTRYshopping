package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PointRateChangeForm {

	private int id;
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[1-9]+$",groups = ValidGroup2.class)
	@Length(min = 1, max = 1, groups = ValidGroup3.class)
	private String pointRate;
}
