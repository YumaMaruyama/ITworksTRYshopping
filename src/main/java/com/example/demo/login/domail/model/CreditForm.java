package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class CreditForm {

	private int expire_date;
	@NotNull(groups = ValidGroup1.class)
	//@NumberFormat(pattern = "\\d{3}")
	@Range(min = 3, max = 3, groups = ValidGroup2.class)
	private int digits_3_code;

	@NotBlank(groups = ValidGroup1.class)
	//@Pattern(regexp = "^[A-Z]{4,30}$")
	@Length(min = 4, max = 30, groups = ValidGroup2.class)
	private String cardName;

	@NotNull(groups = ValidGroup1.class)
	//@NumberFormat(pattern = "\\d{16}")
	@Range(min = 16, max = 16, groups = ValidGroup2.class)
	private int cardNumber;
}
