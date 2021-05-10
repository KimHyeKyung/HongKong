package hongkong.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hongkong.domain.dto.LoginDto;
import hongkong.domain.dto.MemberDto;
import hongkong.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	
	//로그인으로 이동
	@GetMapping("/member/login")
	public String Login(MemberDto dto) {
		return "/member/login";
	}
	
	//login처리하기
	@PostMapping("/member/login")
	public String login(LoginDto dto) {
		service.login(dto);
		return "redirect:/";
	}
	
	
	//회원가입으로 이동
	@GetMapping("/member/join")
	public String goJoin(MemberDto MemberDto) {
		return "/member/join";
	}
	
	//회원가입처리하기 --> dto의 정보를 저장해야한다!!!
	@PostMapping("/member/join")
	public String dojoin(@Valid MemberDto dto, BindingResult bindingResult) {//@Valid를 넣어 제약조건 check
		
		//BindingResult bindingResult로 에러 처리
		if(bindingResult.hasErrors()) {  //만약에 에러가 발생했다면
			//현재 입력된 데이터도 그대로 표현해주자(어떻게 썼길래 에러발생했는지 봐야하니깐)
			//에러메세지를 출력하면 된다.
			return "/member/join"; //에러가 발생하면 join페이지로 이동
		}
		
		service.dojoin(dto);
		return "redirect:/member/login";
	}
	
	//join.html $.post처리
	@ResponseBody
	@PostMapping("/member/emailCheck")
	public void emailCheck(String email) throws IOException {
		service.check(email);
	}
	
	
	
	//logout처리하기
	@GetMapping("/member/logout")
	public String logout() {
		service.logout();
		return"redirect:/";
	}
	
	
	
}