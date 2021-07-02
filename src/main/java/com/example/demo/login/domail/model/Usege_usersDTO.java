package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class Usege_usersDTO {

	private int id;//usege_usersId

	private int user_id;//usersId

	private Date birthday;

	private String address;
}
