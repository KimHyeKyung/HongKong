package hongkong.service;

import java.util.List;

import hongkong.domain.dto.QnaDto;
import hongkong.domain.dto.QnaUpdateDto;

public interface QnaService {

	void saveWrite(QnaDto dto);

	List<QnaDto> getQnaList();

	QnaDto getDetail(long bno);

	void updateDetail(QnaUpdateDto dto);

	void delete(long bno);

	

	

	

}
