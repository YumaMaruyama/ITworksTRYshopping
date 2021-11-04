package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingContractDao;

@Repository
public class ChallengeProgrammingContractDaoJdbcImpl implements ChallengeProgrammingContractDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId,ChallengeProgrammingMessageDTO challengeProgrammingMessagedto) {
		
		int result = jdbc.update("insert into challenge_programming_contract (id,"
				+ " user_id,"
				+ " mail_address,"
				+ " phone_number,"
				+ " contract_project_id,"
				+ " teacher_message1,"
				+ " teacher_message2,"
				+ " teacher_message3,"
				+ " my_message1,"
				+ " my_message2,"
				+ " my_message3)"
				+ " value(?,?,?,?,?,?,?,?,?,?,?)",challengeProgrammingContractdto.getId(),userId,mailAddress,phoneNumber,productId,challengeProgrammingMessagedto.getTeacherMessage1(),challengeProgrammingMessagedto.getTeacherMessage2(),challengeProgrammingMessagedto.getTeacherMessage3(),challengeProgrammingMessagedto.getMyMessage1(),challengeProgrammingMessagedto.getMyMessage2(),challengeProgrammingMessagedto.getMyMessage3());
		
		return result;
	}
	
	public int duplicateCheck(int userId) {
		
		int result = 0;
		
		try {
			int selectUserId = jdbc.queryForObject("select challenge_programming_contract.user_id from challenge_programming_contract where user_id = ?",Integer.class,userId);
			result = 1;
		}catch(NullPointerException | EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		
	return result;
	}
	
	public ChallengeProgrammingContractDTO teacherMessege1Select(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select challenge_programming_contract.id,challenge_programming_contract.user_id,challenge_programming_contract.phone_number,challenge_programming_contract.contract_date,challenge_programming_contract.contract_project_id,challenge_programming_contract.mail_address,challenge_programming_contract.Teacher_message1,challenge_programming_contract.Teacher_message2,challenge_programming_contract.Teacher_message3,challenge_programming_contract.my_message1,challenge_programming_contract.my_message2,challenge_programming_contract.my_message3,challenge_programming.my_name from challenge_programming_contract join challenge_programming on challenge_programming_contract.contract_project_id = challenge_programming.id where contract_project_id = ?",productId);
		
		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();
		challengeProgrammingContractdto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingContractdto.setTeacherName((String)map.get("my_name"));
		
		
		return challengeProgrammingContractdto;
	}
	
	public ChallengeProgrammingContractDTO tm1Mm1Select(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select challenge_programming_contract.id,challenge_programming_contract.user_id,challenge_programming_contract.phone_number,challenge_programming_contract.contract_date,challenge_programming_contract.contract_project_id,challenge_programming_contract.mail_address,challenge_programming_contract.Teacher_message1,challenge_programming_contract.Teacher_message2,challenge_programming_contract.Teacher_message3,challenge_programming_contract.my_message1,challenge_programming_contract.my_message2,challenge_programming_contract.my_message3,users.user_name,challenge_programming.my_name from challenge_programming_contract join users on challenge_programming_contract.user_id = users.id join challenge_programming on challenge_programming_contract.contract_project_id = challenge_programming.id where contract_project_id = ?",productId);
		
		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();
		challengeProgrammingContractdto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingContractdto.setTeacherMessage2((String)map.get("teacher_message2"));
		challengeProgrammingContractdto.setMyMessage1((String)map.get("my_message1"));
		challengeProgrammingContractdto.setTeacherName((String)map.get("my_name"));
		challengeProgrammingContractdto.setUserName((String)map.get("user_name"));
		challengeProgrammingContractdto.setProductId((int)map.get("contract_project_id"));
		
		
		return challengeProgrammingContractdto;
	}
	
	public ChallengeProgrammingContractDTO tm2Mm2Select(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select challenge_programming_contract.id,challenge_programming_contract.user_id,challenge_programming_contract.phone_number,challenge_programming_contract.contract_date,challenge_programming_contract.contract_project_id,challenge_programming_contract.mail_address,challenge_programming_contract.Teacher_message1,challenge_programming_contract.Teacher_message2,challenge_programming_contract.Teacher_message3,challenge_programming_contract.my_message1,challenge_programming_contract.my_message2,challenge_programming_contract.my_message3,users.user_name,challenge_programming.my_name from challenge_programming_contract join users on challenge_programming_contract.user_id = users.id join challenge_programming on challenge_programming_contract.contract_project_id = challenge_programming.id where contract_project_id = ?",productId);
		
		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();
		challengeProgrammingContractdto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingContractdto.setTeacherMessage2((String)map.get("teacher_message2"));
		challengeProgrammingContractdto.setTeacherMessage3((String)map.get("teacher_message3"));
		challengeProgrammingContractdto.setMyMessage1((String)map.get("my_message1"));
		challengeProgrammingContractdto.setMyMessage2((String)map.get("my_message2"));
		challengeProgrammingContractdto.setTeacherName((String)map.get("my_name"));
		challengeProgrammingContractdto.setUserName((String)map.get("user_name"));
		challengeProgrammingContractdto.setProductId((int)map.get("contract_project_id"));
		
		return challengeProgrammingContractdto;
	}
	
	public ChallengeProgrammingContractDTO tm3Mm3select(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select challenge_programming_contract.id,challenge_programming_contract.user_id,challenge_programming_contract.phone_number,challenge_programming_contract.contract_date,challenge_programming_contract.contract_project_id,challenge_programming_contract.mail_address,challenge_programming_contract.Teacher_message1,challenge_programming_contract.Teacher_message2,challenge_programming_contract.Teacher_message3,challenge_programming_contract.my_message1,challenge_programming_contract.my_message2,challenge_programming_contract.my_message3,users.user_name,challenge_programming.my_name from challenge_programming_contract join users on challenge_programming_contract.user_id = users.id join challenge_programming on challenge_programming_contract.contract_project_id = challenge_programming.id where contract_project_id = ?",productId);
		
		
		
		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();
		challengeProgrammingContractdto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingContractdto.setTeacherMessage2((String)map.get("teacher_message2"));
		challengeProgrammingContractdto.setTeacherMessage3((String)map.get("teacher_message3"));
		challengeProgrammingContractdto.setMyMessage1((String)map.get("my_message1"));
		challengeProgrammingContractdto.setMyMessage2((String)map.get("my_message2"));
		challengeProgrammingContractdto.setMyMessage3((String)map.get("my_message3"));
		challengeProgrammingContractdto.setTeacherName((String)map.get("my_name"));
		challengeProgrammingContractdto.setUserName((String)map.get("user_name"));
		challengeProgrammingContractdto.setProductId((int)map.get("contract_project_id"));
		
		return challengeProgrammingContractdto;
	}
	
	public int chatComplete(int productId) {
		int chatCompleteResult = jdbc.update("update challenge_programming_contract set chat_check = 'チャット完了' where contract_project_id = ?",productId);
		
		return chatCompleteResult;
	}
	
	public int selectChatCheck(int productId) {
		
		int chatCheckJudgement = 0;
		
		try {
		String chatCheck = jdbc.queryForObject("select challenge_programming_contract.chat_check from challenge_programming_contract where contract_project_id = ?",String.class,productId);
			if(chatCheck == null) {
				System.out.println("nullが取得");
				chatCheckJudgement = 1;
			}
		}catch(EmptyResultDataAccessException e) {
			System.out.println("キャッチ処理");
			e.printStackTrace();
			chatCheckJudgement = 1;
		}
		
		return chatCheckJudgement;
	}
	
	public int lessonDayInsertOne(String lessonDay,int productId) {
		int result = jdbc.update("update challenge_programming_contract set lesson_day = ? where contract_project_id = ?",lessonDay,productId);
		
		return result;
	}
	
	public String lessonDaySelectOne(int productId) {
		
		String lessonDay;
		
		try {
		lessonDay = jdbc.queryForObject("select challenge_programming_contract.lesson_day from challenge_programming_contract where contract_project_id = ?",String.class,productId);
		
		if(lessonDay == null) {
			lessonDay = "未設定";
		}
		
		}catch(EmptyResultDataAccessException  | NullPointerException e) {
			System.out.println("キャッチ処理");
			e.printStackTrace();
			lessonDay = "未設定";
		}

		return lessonDay;
	}
	
	public int belongingsCheckInsertOne(int productId) {
		int result = jdbc.update("update challenge_programming_contract set belongngs_check = '持ち物チェック完了' where contract_project_id = ?",productId);
		
		return result;
	}
	
	public String belongngsSelectOne(int productId) {
		
		String belongngs;
		
		try {
		belongngs = jdbc.queryForObject("select challenge_programming_contract.belongngs_check from challenge_programming_contract where contract_project_id = ?",String.class,productId);
		
		if(belongngs == null) {
			belongngs = "未確認";
		}
		
		}catch(EmptyResultDataAccessException  | NullPointerException e) {
			System.out.println("キャッチ処理");
			e.printStackTrace();
			belongngs = "未設定";
		}
		
		return belongngs;
	}
	
	public int locationConfirmationInsertOne(int productId) {
		int result = jdbc.update("update challenge_programming_contract set location_confirmation_check = '場所確認完了' where contract_project_id = ?",productId);
		
		return result;
	}
	
	public String locationSelectOne(int productId) {
		String location = jdbc.queryForObject("select challenge_programming_contract.location_confirmation_check from challenge_programming_contract where contract_project_id = ? ",String.class,productId);
		
		return location;
	}
}
