package hongkong.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Qna{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long bno;
	@Column(nullable = false)
	String subject;
	@Column(nullable = false)
	String contents;
	@Column(nullable = false)
	String email;
	@Column(nullable = false)
	String name;
	
	@CreatedDate
	private LocalDateTime created_date;
	
}
