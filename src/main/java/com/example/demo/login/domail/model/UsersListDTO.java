package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class UsersListDTO {

	private int id;

	private String userId;

	private String userName;
	
	private String plan;

	private Date birthday;

	private String address;

}
