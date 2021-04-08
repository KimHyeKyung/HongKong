package hongkong.service;

import java.util.List;

import hongkong.domain.dto.BoardDto;

public interface BoardService {

	List<BoardDto> getReviewList();

	void saveWrite(BoardDto dto);

	BoardDto getDetailList(long bno);

	void editDetail(BoardDto dto);

	void delete(long bno);

}
