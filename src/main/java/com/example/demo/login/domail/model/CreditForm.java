package com.example.demo.login.domail.model;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Data
public class CreditForm {

	private int expire_date;
	//@NotBlank
	@NumberFormat(pattern = "\\d{3}")
	private int digits_3_code;
	//@NotBlank
	@Pattern(regexp = "^[A-Z]{4,30}$")
	private String cardName;
	//@NotBlank
	@NumberFormat(pattern = "\\d{16}")
	private int cardNumber;
}
