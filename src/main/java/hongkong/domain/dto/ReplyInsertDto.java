package hongkong.domain.dto;

import hongkong.domain.entity.Board;
import hongkong.domain.entity.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyInsertDto {
	
	private long boardBno; //댓글의 no값 말고 Board객체 안에 필드 bno값이 필요함(=Board.bno)
	private String text;
	private String replyWriter;
	
	public Reply toEntity() {
		return Reply.builder()
				.text(text)
				.replyWriter(replyWriter)
				.board(Board.builder().bno(boardBno).build())
				.build();
	}
}
