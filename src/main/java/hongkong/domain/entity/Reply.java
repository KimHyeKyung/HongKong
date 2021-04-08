package hongkong.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@DynamicInsert
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rno;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private String replyWriter;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	@ManyToOne()
	@JoinColumn(name = "board_bno")//BoardBno
	private Board board;
	
	//update하기위해 만들었다
	public Reply update(String text) {
		this.text=text;
		return this;
	}

	
	
}
