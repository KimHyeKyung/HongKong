package hongkong.domain.dto;

import java.time.LocalDateTime;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import hongkong.domain.entity.Member;
import hongkong.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Data
public class MemberDto {//인증된 멤버 dto

	@Size(min = 2,max = 50)
	@Pattern(regexp = "[a-zA-z0-9]+@[-zA-z0-9]+[.]+[a-zA-z.]+", message = "@를 사용한 이메일 형식을 맞춰주세요")
	private String email;
	
	@NotBlank(message = "이름은 필수항목입니다.")
	@Size(min = 2,max = 20, message = "2글자 이상 30글자까지 가능합니다.")
	private String name;
	
	@NotBlank(message = "비밀번호는 필수항목입니다.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&/?]).{8,16}", message = "영문소문자, 숫자, 특수문자를 포함한 최소 8글자이상 16까지 표현하여햐 합니다.")
	private String password;
	
	
	private LocalDateTime regDate; //자동으로 셋팅되어서 builder에 포함 안시켰다

	public Member toEntity() {
		Member entity = Member.builder()
						.email(email)
						.name(name)
						.password(password)
						.build();
		
		//회원가입하면 user권한 부여
		entity.addRole(Role.USER);
		
		return entity;
	}

	
}
