package hongkong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import hongkong.domain.dto.MailDto;
import hongkong.utils.MailHandler;

@Service
public class EmailService {
	
	private static final String FROM_ADDRESS = "kimhye_1218@gmail.com";
	
	@Autowired
	private JavaMailSender mailSender;
	
	//@Autowired
	//MemberRepository repository;
	
	
	//임의의 비밀번호 만드는 메서드
	public String getTempPassword() {
		char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9',
									'a','b','c','d','e','f','g','h','i','j','k','l','n','m','o','p','q','r','s','t','u','v','w','u','r','x','y','z'};
		String str = "";
		int idx=0;
		for(int i = 0; i<10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		return str;
	}
	
	//이메일 전송 메서드
	public void mailSend(MailDto mailDto) {
		System.out.println("이메일 전송 완료");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(EmailService.FROM_ADDRESS);
		
		message.setTo(mailDto.getAddress());
		message.setSubject(mailDto.getTitle());
		message.setText(mailDto.getMessage());
		
		mailSender.send(message);
	}
	
	//helper를 이용하면 첨부파일이나 이미지 사용이 가능하다.
	public void mailSendWithHelper(MailDto mailDto) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);
			
			//1. 보내는 사람
			mailHandler.setFrom(FROM_ADDRESS);
			
			//2. 받는 사람
			mailHandler.setTo(mailDto.getAddress());
			mailHandler.setSubject(mailDto.getTitle());
			
			/* MailTemplateService: 별도의 템플릿을 적용해서 layout을 작성할 수 있다. (내용이 길어지면 MailTemplateService사용하는게 좋음)*/
			String msgHtml="<p>" + mailDto.getMessage() + "</p>";
					msgHtml+="<img src='cid:mail-img'/>";
			mailHandler.setText(msgHtml, true);
			mailHandler.setInline("mail-img", "/images/HKTB-logo.svg");
			mailHandler.setAttachment("welcome.txt","/files/welcome.txt");
			
			//3. send
			mailHandler.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}

