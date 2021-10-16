package com.example.demo.login.domail.repository.jdbc;

import java.util.List;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;

public interface ChallengeProgrammingDao {

	public List<ChallengeProgrammingDTO> projectSelectMany();
	
	public ChallengeProgrammingDTO projectSelectOne(int projectId);
}
