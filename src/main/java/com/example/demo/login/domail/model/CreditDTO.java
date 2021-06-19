package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class CreditDTO {

	private int id;

	private Date registration_date;

	private int expire_date;

	private String cardName;

	private int cardNumber;

	private String user_id;
}
