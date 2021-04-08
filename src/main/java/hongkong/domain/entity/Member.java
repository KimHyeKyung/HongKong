package hongkong.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@DynamicInsert
@DynamicUpdate
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Member extends DateEntity{
	
	@Id
	@Column(nullable = false, unique = true) //unique = true로 중복 안되게 해주자
	private String email;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@CreatedDate
	private LocalDateTime regDate;
	
	//defalt값이 false라 굳이 안넣었다
	private boolean socialUser;
	
	@Builder.Default //따로 값을 넣지 않아도 빌더에서 디폴트로 사용할게요
	@Enumerated(EnumType.STRING) //Enum의 상수값(0, 1, 2)을 문자열값으로 넣을래요
	@ElementCollection(fetch = FetchType.LAZY) //@ElementCollection: collection을 이용해서 자동으로 클래스에 종속적으로 사용되는 테이블을 만들어준다
	private Set<Role> roleSet = new HashSet<Role>();
	
	//데이터 셋팅 메서드
	public void addRole(Role role) {
		roleSet.add(role);
	}
	
	public Member updateName(String name){
		this.name=name;
		return this;
	}
	
	

	
	
	
	

}
