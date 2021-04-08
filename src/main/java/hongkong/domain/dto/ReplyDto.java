package hongkong.domain.dto;

import java.time.LocalDateTime;

import hongkong.domain.entity.Reply;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ReplyDto {

	private long rno;
	private String text;
	private String replyWriter;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	//entity데이터를 dto로 바꾼다
	public ReplyDto(Reply entity) {
		this.rno = entity.getRno();
		this.text = entity.getText();
		this.replyWriter = entity.getReplyWriter();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
	}
	
	
	
}
