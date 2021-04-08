package hongkong.domain.dto;

import hongkong.domain.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {
	
	/*
		HKMemberLoginDto를 따로 만들자!!
		원래는 HKMemberDto를 다시 사용해서 login처리 하려고 했는데 
		회원가입때문에 걸어놓은 validation때문에 
		나는 email, name만 로그인때 사용할건데 password validation이 값이 안들어오니깐
		자꾸 join페이지로 넘겨버렸다
		그래서 HKMemberLoginDto를 따로만들어서 사용하는게 낫다!
	*/
	
	String email;
	String name;
	String password;
	
	
	public LoginDto (Member entity) {
		this.email=entity.getEmail();
		this.name=entity.getName();	
		this.password=entity.getPassword();	
	}

}
	
