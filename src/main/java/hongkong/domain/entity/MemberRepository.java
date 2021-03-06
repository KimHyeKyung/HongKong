package hongkong.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

	Optional<Member> findByEmail(String email);

	Optional<Member> findByEmailAndSocialUser(String username, boolean b);

}
