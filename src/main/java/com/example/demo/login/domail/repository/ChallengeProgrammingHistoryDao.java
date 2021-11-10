package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingHistoryDTO;

public interface ChallengeProgrammingHistoryDao {

	public int historyInsertOne(ChallengeProgrammingDTO challengeprogrammingDTO,ChallengeProgrammingHistoryDTO challengeProgrammingHistoryDTO);
}
