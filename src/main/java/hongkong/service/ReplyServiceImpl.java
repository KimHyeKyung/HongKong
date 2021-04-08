package hongkong.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hongkong.domain.dto.ReplyDto;
import hongkong.domain.dto.ReplyInsertDto;
import hongkong.domain.entity.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyRepository reRepository;
	
/*
	@Transactional
	@Override
	public List<ReplyDto> getReplyList(long boardNo) {
		Board result=boardRepository.findById(boardNo).get();
		return result.getReply().stream()
				.map(ReplyDto::new)
				.collect(Collectors.toList());
	}
*/
	@Transactional
	@Override
	public void save(ReplyInsertDto dto) {
		reRepository.save(dto.toEntity());
	}


	@Override
	public List<ReplyDto> getReplyList(long boardBno) {
		return reRepository.findAllByBoardBno(boardBno)
							.stream()
							.map(ReplyDto::new)
							.collect(Collectors.toList()); 
	}


	@Transactional
	@Override
	public void update(long rno, String text) {
		reRepository.findById(rno)
					.map(result->result.update(text))
					.orElse(null);
		
	}


	@Override
	public void delete(ReplyDto dto) {
		reRepository.deleteById(dto.getRno());
	}

	
	
	 
}
