package hongkong.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import hongkong.domain.dto.BoardDto;
import hongkong.domain.entity.BoardRepository;


@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository repository;

	@Override
	public List<BoardDto> getReviewList() {
		
		Sort sort=Sort.by(Direction.DESC, "bno");
		//전체를 가져와야한다
		//DB에 저장되어있는 Entity값을 Dto로 가져와서 뿌려야 한다.
		return repository.findAll(sort)
					.stream()
					.map(BoardDto::new) //dto에 entity를 dto로 변환하는 작업을 해준다
					.collect(Collectors.toList());
	}

	@Override
	public void saveWrite(BoardDto dto) {
		//dto->Entity로 DB에 저장해야한다.
		//그러기 위해서는 1. toEntity를 dto에 만들기 2.Entity에도 생성자 만들기
		repository.save(dto.toEntity());
	}
	

	//제목을 누르면 해당 제목의 detail페이지로 이동
	@Override
	public BoardDto getDetailList(long bno) {
		
		BoardDto dto=repository.findById(bno)
		.map(BoardDto::new)
		.orElse(null);
		
		return dto;
	}

	//detail페이지에서 수정완료 버튼 누르면 detail페이지 다시 보여주세요
	@Transactional
	@Override
	public void editDetail(BoardDto dto) {
		//먼저 bno값을 찾은 다음
		//자신의 entity값을 변환된 entity로 바꿔줘야한다.
		repository.findById(dto.getBno())
					.map(entity->entity.update(dto))
					.orElse(null);
	}

	@Override
	public void delete(long bno) {
		repository.deleteById(bno);
	}    

	
	

	
	

	

}
