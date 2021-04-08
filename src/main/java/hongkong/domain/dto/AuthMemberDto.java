package hongkong.domain.dto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import hongkong.domain.entity.Member;
import lombok.Data;

//UserDetails 사용자형태로 만든 클래스
@Data
public class AuthMemberDto extends User implements OAuth2User{
	//loadUserByUsername가 실행되면 넘어오는 데이터
	
	//password는 복호화가 안돼서 알 수 없으니 집어넣지 않는다
	private String email;
	private String name;
	private boolean socialUser;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	Map<String, Object> attributes;
	
	public AuthMemberDto(Member member) {
		//super생성자는 첫줄에 표현해야한다.
		//세번째 인자는 Collection<? extends GrantedAuthority> authorities형태
		super(  member.getEmail(),
				member.getPassword(),
				member.getRoleSet()//현재: Set<Role>형태
								 //Set<Role>형태를 --> Set<SimpleGrantedAuthority>로 바꾼다
								.stream()
								.map(role->new SimpleGrantedAuthority(role.getKey()))
								.collect(Collectors.toSet()));

		//Collection<? extends GrantedAuthority>
		this.email = member.getEmail();
		this.name = member.getName();
		this.socialUser = member.isSocialUser();
		this.createdDate = member.getCreatedDate();
		this.updatedDate = member.getUpdatedDate();
	}

	
	
	
}
