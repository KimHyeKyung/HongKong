package hongkong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hongkong.domain.dto.QnaDto;
import hongkong.domain.dto.QnaUpdateDto;

@Mapper
public interface QnaMapper {

	void saveWrite(QnaDto dto);

	List<QnaDto> findAll();

	QnaDto getDetail(long bno);

	void updateDetail(QnaUpdateDto dto);

	void delete(long bno);

}
