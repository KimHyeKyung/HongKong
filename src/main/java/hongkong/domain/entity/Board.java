package hongkong.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import hongkong.domain.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@DynamicInsert
@DynamicUpdate
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
public class Board {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bno;
	
	@Column(nullable = false)
	private String subject;
	
	@Column(nullable = false)
	private String contents;
	
	@Column(nullable = false)
	private String creatorId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_email")
	private Member member;
	
	//글에 들어가면 댓글들이 여러개 있어야 한다
	@OneToMany(mappedBy = "board")
	List<Reply> reply;
	
	@Column
	@ColumnDefault("0")
	private int readCount;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	public Board update(BoardDto dto) {
		this.subject=dto.getSubject();
		this.contents=dto.getContents();
		return this;
	}

	public Board countup() {
		this.readCount=readCount+1;
		return this;
	}

	
}
