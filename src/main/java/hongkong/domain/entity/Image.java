package hongkong.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import hongkong.domain.dto.ImgDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long no;
	String subject;
	String contents;
	String creatorId;
	

	//CascadeType.ALL: 모든 변경에 대한 전이
	//CascadeType.PERSIST: 저장시에만 전이
	//CascadeType.MERGE: 병합시에만 전이
	//CascadeType.REMOVE: 삭제시에만 전이
	//CascadeType.REFRESH: 엔티티매니저 refresh()호출시 전이
	//CascadeType.DETACH: 부모엔티티가 detach되면 자식 엔티티 역시 detach
	//fetch = FetchType.EAGER : 즉시 로딩
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//데이터를 같이 넣어주세요
	@JoinColumn(name = "fno")//imgFile안에 pk를 fno란 이름의fk로 쓸게요!
	ImageFile img;

	@Builder //usingfild사용한다음 @Builder넣어준다
	public Image(String subject, String contents, String creatorId, ImageFile img) {
 		this.subject = subject;
		this.contents = contents;
		this.creatorId = creatorId;
		this.img = img;
	}
	
	/*
	 * //수정된 데이터를 바꾸기 위해서는 set메서드가 있어야 하는데 없으니깐 따로 메서드를 만들자 update로 public HKImg
	 * update(HKImgRequestDto dto) { this.subject = dto.getSubject(); this.contents
	 * = dto.getContents(); return this; }
	 */

	public Image update(ImgDto dto) {
		this.subject = dto.getSubject();
		this.contents = dto.getContents();
		this.no = dto.getNo();
		return this;
	}
	
	
}
