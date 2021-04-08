package hongkong.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hongkong.domain.dto.AuthMemberDto;
import hongkong.domain.entity.Member;
import hongkong.domain.entity.MemberRepository;

@Service
public class MemberDetailService implements UserDetailsService{
	
	@Autowired
	MemberRepository repository;
	
	@Transactional
	@Override
	//userName=ID
	//UserDetails을 사용할때 우리가 원하는 DTO를 만들자
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username: "+username);
		//인증처리를 한다
		//1. 먼저 username=ID가 DB에 존재하는지 파악
		Optional<Member> result = repository.findByEmailAndSocialUser(username, false);
		
		if(result.isEmpty()) {
			//result가 null이면 결과가 존재하지 않는거니깐 exception처리하자
			throw new UsernameNotFoundException("존재하지 않는 이메일입니다."); 
		}
		//ID존재하면 ID에 해당하는 데이터를 가져오자
			//Memb memb = result.get();
			//AuthMembDto authMembDto = new AuthMembDto(memb);
			//return AuthMembDto;
		//2.ID확인 끝났으니 password확인
		
		return result.map(AuthMemberDto::new).get();
	}
}
