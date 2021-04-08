package hongkong.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//생성자를 만들어준다
@Getter
public enum Role {

	//각 회원이 어디까지 접근 가능한지 처리 해야한다.
	//0, 1, 2에 해당하는 상수정보
	//final필드의 값
	USER("ROLE_USER","일반 사용자"),
	ADMIN("ROLE_ADMIN","최고 관리자");
	
	private final String key;
	private final String title;
}
