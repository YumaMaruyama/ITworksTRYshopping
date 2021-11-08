package com.example.demo.login.domail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


	 
	@Service
	public class MailService {
		@Autowired
	    JavaMailSender mailSender;

		
		  public void purchaseSendMail() throws MessagingException {
			  
	        String typeText = "text.txt";
	        //ファイルも一緒に送信するため指定
	       // FileSystemResource fileResource = new FileSystemResource("/Users/maruy/git/springBoot_shopping/src/main/resources/static/css/qrcode.png");
	        // メッセージクラス
	        MimeMessage mimeMsg = mailSender.createMimeMessage();
	        // ファイル対応ヘルパークラス
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);

	        //送信者アドレス(icloudやgmailにも対応)
	        helper.setFrom("maruyubon@gmail.com");
	        //送信先アドレス(icloudやgmailにも対応);
	        helper.setTo("marubon@icloud.com");
	        //タイトル
	        helper.setSubject("メールタイトル");
	        //内容
	        helper.setText("メール本文");
	        //ファイル(※一つ)
	      //  helper.addAttachment("qrcode.png", fileResource);

	        // sendでメール送信完了
	        mailSender.send(mimeMsg);

	    }
}
