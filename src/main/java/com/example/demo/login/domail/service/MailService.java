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

	public void signupSendMail() throws MessagingException {
		String typeText = "text.txt";
		//ファイルも一緒に送信するため指定
		// FileSystemResource fileResource = new FileSystemResource("/Users/maruy/git/springBoot_shopping/src/main/resources/static/css/qrcode.png");
		// メッセージクラス
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		// ファイル対応ヘルパークラス
		MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);

		//送信者アドレス(icloudやgmailにも対応)
		helper.setFrom("ITworksTRYshopping");
		//送信先アドレス(icloudやgmailにも対応);
		helper.setTo("marubon@icloud.com");
		//タイトル
		helper.setSubject("新規ユーザー登録のおしらせ");
		//内容
		helper.setText("新規ユーザー登録をしていただきありがとうございます。今後ともITworksTRYshoppingをよろしくお願いいたします。");
		//ファイル(※一つ)
		//  helper.addAttachment("qrcode.png", fileResource);

		// sendでメール送信完了
		mailSender.send(mimeMsg);
	}

	public void purchaseSendMail(String mailAddress) throws MessagingException {

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
		helper.setTo(mailAddress);
		//タイトル
		helper.setSubject("ご購入完了のお知らせ");
		//内容
		helper.setText(
				"ご購入ありがとうございます。商品がお手元に届くまで長くて約10日ほどかかります。しばらくお待ちください。キャンセルは７日間、またはキャンセル手続きが終了するまで受け付けます。商品が倉庫から発送済みの場合は手続きに従って返送を行ってください。キャンセルについては商品履歴から可能になっております。また、何かお困りごとがありましたらお問い合わせからお気軽にお問い合わせください。今後ともITworksTRYshoppingをよろしくお願いいたします。");
		//ファイル(※一つ)
		//  helper.addAttachment("qrcode.png", fileResource);

		// sendでメール送信完了
		mailSender.send(mimeMsg);

	}
	
	
	public void deliverySendMail(String mailAddress) throws MessagingException {

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
		helper.setTo(mailAddress);
		//タイトル
		helper.setSubject("注文商品の発送のお知らせ");
		//内容
		helper.setText("お世話になっております。先日あなた様が購入しました商品が発送されました。到着までしばらくお待ちください。到着後注文した商品と違った場合や、破損した場合はキャンセル手続きやお問い合わせをご利用ください。商品の確認後、よろしければレビーの方をしていただけると幸いです。今後ともITworksTRYshoppingをよろしくお願いいたします。");
		//ファイル(※一つ)
		//  helper.addAttachment("qrcode.png", fileResource);

		// sendでメール送信完了
		mailSender.send(mimeMsg);

	}

	public void adminSendMail(String mailAddress) throws MessagingException {

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
		helper.setSubject(mailAddress + "宛てに購入メールが送信されました。");
		//内容
		helper.setText("発送手続きをお願いします。");
		//ファイル(※一つ)
		//  helper.addAttachment("qrcode.png", fileResource);

		// sendでメール送信完了
		mailSender.send(mimeMsg);

	}
}
