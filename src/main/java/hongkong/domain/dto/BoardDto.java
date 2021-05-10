package hongkong.domain.dto;

import java.time.LocalDateTime;

import hongkong.domain.entity.Board;
import hongkong.domain.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardDto {

	private long bno;
	private String subject;
	private String contents;
	private String creatorId;
	private int readCount;
	private LocalDateTime createdDate;
	private String email;
	
	
	//select: DB에 있는 list목록을 가져오기 위해서 entity를 dto로 바꾸는 메서드
	public BoardDto(Board entity) {
		this.bno = entity.getBno();
		this.subject = entity.getSubject();
		this.contents = entity.getContents();
		this.creatorId = entity.getCreatorId();
		this.createdDate = entity.getCreatedDate();
		this.readCount = entity.getReadCount();
		this.email=entity.getMember().getEmail();
		
	}

	public Board toEntity() {
		return Board.builder()
				.subject(subject)
				.contents(contents)
				.creatorId(creatorId)
				.createdDate(createdDate)
				.member(Member.builder().email(email).build())
				.build();
	}


	
	
}
