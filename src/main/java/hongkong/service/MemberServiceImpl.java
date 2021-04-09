package hongkong.service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hongkong.domain.dto.LoginDto;
import hongkong.domain.dto.MailDto;
import hongkong.domain.dto.MemberDto;
import hongkong.domain.entity.Member;
import hongkong.domain.entity.MemberRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j //log.debug쓰려고 적어줌
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	
	//회원가입정보 DB에 저장
	@Override
	public void dojoin(MemberDto dto) {
		//dto의 정보를 entity로 저장해야한다.
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		Member resultEntity = repository.save(dto.toEntity());
		if(resultEntity==null) {
			log.debug("회원가입오류");
		}
		
		//회원인증을 통한 메일전송 --> 소셜 로그인 아닌거(일반회원들 막 가입할 가능성 높아서)
		//인증코드 만들기, 메일전송 로직 ...
		MailDto mailDto = MailDto.builder()
								.address(dto.getEmail())
								.title("[HongKong Communication]테스트 헬퍼 인증메일 입니다. 회원가입을 축하합니다.")
								.message("인증메일입니다.")
								.build();
		//emailService.mailSend(mailDto);
		emailService.mailSendWithHelper(mailDto);

		
	}
	//----------------------------------------
	//$.post ajax처리하기
	@Autowired
	private HttpServletResponse response;
	
	@Override
	public void check(String email) throws IOException {
		Member result = repository.findByEmail(email).orElse(null);
		
		//email이 존재하면 객체, 존재하지 않으면 null
		int check=0;
		if(result==null) { //DB에 이메일이 존재하지 않으면 사용가능 이메일
			check=1;
		}
		
		response.getOutputStream().print(check);
	}
	//----------------------------------------
	@Autowired
	private HttpSession httpsession; //로그인 처리한거 session에 저장
	
	//로그인처리
	@Override
	public void login(LoginDto dto) {
	
		//일치하는 email데이터가 있는지 확인
		Optional<Member> op = repository.findByEmail(dto.getEmail());
		
		if(op.isPresent()) {
			log.debug("일치하는 데이터 존재");
			Member member = op.get();
			boolean check = passwordEncoder.matches(dto.getPassword(), member.getPassword());
			log.debug("password"+check);
			if(check) { //이메일과 비밀번호가 알맞으면
				LoginDto logInfo = new LoginDto(member);
				httpsession.setAttribute("logInfo", logInfo);
			}else { //이메일이 맞지만 비밀번호가 안맞음
				log.debug("비밀번호가 다릅니다.");
			}
		}else { //이메일 자체가 없음
			log.debug("email이 존재하지 않습니다.");
		}
	
	}

	//logout처리
	@Override
	public void logout() {
		httpsession.removeAttribute("logInfo");
		
	}

	
	//----------------------------------------------------//

	
}