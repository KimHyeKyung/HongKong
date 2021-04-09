package hongkong.utils;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
	private JavaMailSender javaMailSender;
	private MimeMessage mimeMessage;
	private MimeMessageHelper mimeMessageHelper;
	
	public MailHandler(JavaMailSender javaMailSender) throws MessagingException {
		this.javaMailSender=javaMailSender;
		mimeMessage = javaMailSender.createMimeMessage(); //이미지나 태그형태도 전송 가능
		
		mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
	}

	//보내는 사람(company)
	public void setFrom(String fromAddress) throws MessagingException {
		mimeMessageHelper.setFrom(fromAddress);
	}
	
	//받는 사람
	public void setTo(String address) throws MessagingException {
		mimeMessageHelper.setTo(address);
	}
	
	//제목
	public void setSubject(String subject) throws MessagingException {
		mimeMessageHelper.setSubject(subject);
	}
	
	//내용
	public void setText(String text, boolean useHtml) throws MessagingException {
		mimeMessageHelper.setText(text, useHtml);
	}
	
	//첨부파일
	public void setAttachment(String displayFileName, String filePath) throws MessagingException, IOException {
		File file = new ClassPathResource("static"+filePath).getFile();
		mimeMessageHelper.addAttachment(displayFileName, file);
	}	

	//이미지
	public void setInline(String contentId, String filePath) throws MessagingException, IOException {
		File file = new ClassPathResource("static"+filePath).getFile();
		mimeMessageHelper.addInline(contentId, file);
	}
	
	//메일발송 메서드
	public void send() {
		javaMailSender.send(mimeMessage); //mimeMessage를 담아서 send한다
	}
	

}
