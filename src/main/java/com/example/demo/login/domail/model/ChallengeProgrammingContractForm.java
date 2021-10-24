package com.example.demo.login.domail.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ChallengeProgrammingContractForm {

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 11, max = 11, groups = ValidGroup3.class)
	private String phoneNumber;
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	@Length(min = 1, max = 50, groups = ValidGroup3.class)
	private String mailAddress;
}
