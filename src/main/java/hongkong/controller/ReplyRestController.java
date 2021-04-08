package hongkong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hongkong.domain.dto.ReplyDto;
import hongkong.domain.dto.ReplyInsertDto;
import hongkong.service.ReplyService;

@RestController//@RestController: @Controller + @ResponseBody ajax로 처리하는건 @RestController가 효과적 -> ajax와 같이 쓰면 좋다
public class ReplyRestController {

	@Autowired
	ReplyService service;
	
	//현재 boardBno에 해당하는 댓글 다 read
	//@ResponseBody: @RestController에 의해 생략
	//List<ReplyDto>를 json data로 리턴
	@GetMapping("/rest/reply/{boardBno}")
	public ModelAndView getReplyList(@PathVariable long boardBno, ModelAndView mv) {
		mv.setViewName("/community/reply/replyList");
		mv.addObject("replies",service.getReplyList(boardBno));
		return mv;
	}
	
	//댓글생성
	//produces속성을 이용해서 Response의 Content-Type을 제어
	@PostMapping(value = "/rest/reply", produces = "application/text;charset-utf-8" )
	public String write(ReplyInsertDto dto) {
		service.save(dto);
		return "등록완료!";
	}
	
	//댓글수정 put은 수정할때
	@PutMapping("/rest/reply/{rno}")
	public void update(@PathVariable long rno, String text) {
		service.update(rno,text);
	}
	
	@DeleteMapping("/rest/reply/")
	public void delete(ReplyDto dto) {
		service.delete(dto);
	}
	
	
}
