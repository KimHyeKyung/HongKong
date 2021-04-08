package hongkong.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hongkong.domain.dto.QnaDto;
import hongkong.domain.dto.QnaUpdateDto;
import hongkong.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaMapper qnaMapper;

	@Override
	public void saveWrite(QnaDto dto) {
		qnaMapper.saveWrite(dto);
		
	}

	@Override
	public List<QnaDto> getQnaList() {
		
		return qnaMapper.findAll();
	}

	@Override
	public QnaDto getDetail(long bno) {
		
		return qnaMapper.getDetail(bno);
	}

	@Transactional
	@Override
	public void updateDetail(QnaUpdateDto dto) {
		qnaMapper.updateDetail(dto);
		
	}

	@Override
	public void delete(long bno) {
		qnaMapper.delete(bno);
		
	}


	
	


	
	
	
	


	
}
