package hongkong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import hongkong.domain.entity.Member;
import hongkong.domain.entity.MemberRepository;
import hongkong.domain.entity.Role;



@SpringBootTest
class HongkongApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberRepository memberRepository;
	
	//@Test
	void contextLoads() {
	}
	
	
	
	@Test
	void insertAdmin() {
		Member entity=Member.builder()
				.email("admin@test.com")
				.name("관리자")
				.socialUser(false)
				.password(passwordEncoder.encode("1234"))
				.build();
		entity.addRole(Role.USER);
		entity.addRole(Role.ADMIN);
		
		memberRepository.save(entity);
		
	}
	
}
