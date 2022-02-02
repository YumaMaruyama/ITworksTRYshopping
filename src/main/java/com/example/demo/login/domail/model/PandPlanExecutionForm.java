package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PandPlanExecutionForm {

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 3, max = 3, groups = ValidGroup3.class)
	private String digits_3_code;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[A-Z]+$", groups = ValidGroup2.class)
	@Length(min = 4, max = 30, groups = ValidGroup3.class)
	private String cardName;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 16, max = 16, groups = ValidGroup3.class)
	private String cardNumber;
	
}
