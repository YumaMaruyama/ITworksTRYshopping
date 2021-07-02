package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class CreditDTO {

	private int id;

	private Date registration_date;

	private String digits_3_code;

	private String cardName;

	private String cardNumber;

	private String user_id;
}
