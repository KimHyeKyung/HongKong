package hongkong.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QnaDto {
	
	private long bno;
	private String subject;
	private String contents;
	private String email;
	private String name;
	private LocalDateTime created_date;	
	
}
