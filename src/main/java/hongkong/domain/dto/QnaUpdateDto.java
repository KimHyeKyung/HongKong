package hongkong.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QnaUpdateDto {

	private long bno;
	private String subject;
	private String contents;
	
}
