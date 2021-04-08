package hongkong.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import hongkong.domain.dto.AuthMemberDto;
import hongkong.domain.entity.Member;
import hongkong.domain.entity.MemberRepository;
import hongkong.domain.entity.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberOAuth2UserService extends DefaultOAuth2UserService{

	@Autowired
	MemberRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		log.info("*******************************");
		log.info("userRequest:"+userRequest);
		
		String clientName = userRequest.getClientRegistration().getClientName();
		log.info("clientName : "+clientName);
		log.info("*******************************");
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		oAuth2User.getAttributes().forEach((k,v)->{
			log.info(k+" : "+v);
		});
		
		//social정보를 DB에 저장
		Member member = saveSocialMember(oAuth2User, clientName); 
		
		//dto에 저장한 정보를 넘겨준다
		AuthMemberDto dto = new AuthMemberDto(member);
		 
		dto.setAttributes(oAuth2User.getAttributes());
		return dto;
	}

	
	@Transactional
	private Member saveSocialMember(OAuth2User oAuth2User, String clientName) {
		String email = null;
		String name = null;
		if(clientName.equals("Google")) {
			email = oAuth2User.getAttribute("email");//Attribute중에서 이메일만 가져와주세요
			name = oAuth2User.getAttribute("name");
		}else if(clientName.equals("Naver")){
			Map<String,String> response = (Map<String,String>)oAuth2User.getAttribute("response");
			email=response.get("email");
			name=response.get("name");
		}else if(clientName.equals("Kakao")) {
			Map<String,Object> kakao_account = (Map<String,Object>)oAuth2User.getAttribute("kakao_account");
			email=(String)kakao_account.get("email");
			Map<String,String> profile = (Map<String,String>)kakao_account.get("profile");
			name=profile.get("nickname");
		}
		log.info("name : "+name);
		log.info("email : "+email);
		
		Optional<Member> result = repository.findById(email);// 이메일이 존재하는지 확인해보자
		//이미 이메일이 존재하면
		if(result.isPresent()) {
			//DB의 이름이 다른경우 update해주자
			Member member = result.get();
			if( name.equals(member.getName()) ) {
				return member;
			}
			member.updateName(name);
			return member;
		}
		
		Member member = Member.builder()
						.email(email)
						.name(name)
						.password(passwordEncoder.encode("1111"))
						.roleSet( new HashSet<Role>())
						.socialUser(true)
						.build();
		System.out.println("RoleSet : "+member.getRoleSet());
		member.addRole(Role.USER); //Role을 USER로 등록
		member = repository.save(member);
		return member;
	}
}
