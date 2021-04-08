package hongkong.service;

import java.util.List;

import hongkong.domain.dto.ReplyDto;
import hongkong.domain.dto.ReplyInsertDto;

public interface ReplyService {

	List<ReplyDto> getReplyList(long boardBno);

	void save(ReplyInsertDto dto);

	void update(long rno, String text);

	void delete(ReplyDto dto);


}
