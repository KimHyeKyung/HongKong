package hongkong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hongkong.domain.dto.BoardDto;
import hongkong.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service; 
	
	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
	//ModelAndView: addObject()를 이용해 파라미터에 객체값을 넘김
	//review페이지에 DB에 저장된 list들을 보여주세요
	@GetMapping("/community/review/list")
	public ModelAndView getReviewList() {
		ModelAndView mv = new ModelAndView("/community/review/list"); //데이터를 ModelAndView로 담아가고 보여주기 위해서
		List<BoardDto> list = service.getReviewList();//일 시키면서 no나 id가 필요 없으니 ()안에는 비운다
		mv.addObject("list",list); //페이지를 이동할 때 list란 이름으로 가져가자
		return mv;
	}
	
	//write 페이지 보여주세요
	@GetMapping("/community/review/write")
	public String goReview() {
	  return "/community/review/write"; 
	}
	

	//write페이지에서 글쓰기 버튼 누르면 내용이 DB에 저장 후 review페이지로 다시 가주세요 -> save
	@PostMapping("/community/review/list")
	public String saveWrite(BoardDto dto) {
		service.saveWrite(dto);
		return "redirect:/community/review/list";
	}
	
	//글의 제목을 누르면 detail페이지로 이동해주세요
	@GetMapping("/community/review/detail/{bno}")
	public ModelAndView getDetailList(@PathVariable long bno) {
		ModelAndView mv = new ModelAndView("/community/review/detail");
		BoardDto detail = service.getDetailList(bno);
		mv.addObject("detail",detail);
		return mv;
	}
	
	//detail페이지에서 수정완료 버튼 누르면 detail페이지 다시 보여주세요
	@PostMapping("/community/review/detail")
	public String editDetail(BoardDto dto) {
		service.editDetail(dto);
		return "redirect:/community/review/detail/"+dto.getBno();
	}
	
	//삭제버튼 누르면 삭제
	@GetMapping("/community/review/delete/{bno}")
	public String delete(@PathVariable long bno) {
		service.delete(bno);
		return "redirect:/community/review/list";
	}

	
}
