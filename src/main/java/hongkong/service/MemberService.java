package hongkong.service;

import java.io.IOException;

import hongkong.domain.dto.LoginDto;
import hongkong.domain.dto.MemberDto;

public interface MemberService {

	void dojoin(MemberDto dto);

	void check(String email) throws IOException;

	void logout();

	void login(LoginDto dto);

	


}
