package hongkong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class HKSecurityConfig extends WebSecurityConfigurerAdapter{
/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and().authorizeRequests() //요청한 주소에 대한 권한설정
					.antMatchers("/**", "/css/**" , "/images/**", "/js/**").permitAll();
	}
*/
	
////////////////////////////////////////////////////////////////////////////////////////////////
	//config에서 security 인증설정
	//인증매니저가 provider에게 비밀번호랑 아이디를 토큰타입으로 넘겨서 과정들을 진행
	//provider는 내부적으로 userDetailService를 이용해서 처리
	//ID를 먼저 체크하고 있으면 그제서야 password체크
	///userDetailService는 아이디 비밀번호 조건이 만족하면 DB에서 정보를 읽어서 details라는 걸 제공
	//security처리할 때 userName=ID에 해당하는 단어
	
	
	//@Bean등록은 메서드처럼 등록
	//PasswordEncoder는 BCryptPasswordEncoder를 사용해서 인코딩 할게요
	//BCryptPasswordEncoder: 특징1. 복호화 불가, 2. 매번 암호화된 값이 다르다, 3. matches를 사용해서 일치여부 확인
	@Bean
	public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
	}
	
	//권한,인가,허가(Authorization) -> url주소에 대해서 접근을 할지 말지 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf사용 안할게요
			.authorizeRequests()
				.antMatchers("/","/log/**","/**", "/css/**" , "/images/**", "/js/**").permitAll() // "/"로 시작하는 모든 주소는 허가됩니다.
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()//이미 권한을 획득한 사람들
			//.and()는 http.이라고 생각하면 된다
			.and().formLogin()
							.loginPage("/member/login") //formLogin("/login") 인증에 문제가 생겼을때 지정된 로그인 페이지로 이동
							.loginProcessingUrl("/login")
							.usernameParameter("email") //username으로 받아온 값이 DB에는 email로 저장되어있으니 파라미터 이름을 바꾼다
							.passwordParameter("password")
			.and().logout()//만약 csrf토큰 사용시 반드시 POST로만 로그아웃 처리해야한다. -> form태그를 이용해서 post로 처리
			//구글 클라이언트 추가
			.and().oauth2Login();
	}
	
	
}
