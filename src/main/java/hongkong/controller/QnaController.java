package hongkong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import hongkong.domain.dto.QnaDto;
import hongkong.domain.dto.QnaUpdateDto;
import hongkong.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	QnaService qnaService;
	
	//qna페이지로 이동
	@GetMapping("/cs/qnaList")
	public ModelAndView getQnaList(ModelAndView mv) {
		List<QnaDto> list = qnaService.getQnaList();
		System.out.println(list);
		mv.addObject("list", list);
		mv.setViewName("/cs/qnaList");
		return mv;
	}
	
	//qna에서 글쓰기 페이지로 이동
	@GetMapping("/cs/qnaWrite")
	public String qnaWrite() {
		return "/cs/qnaWrite";
	}
	
	//qna글쓰기하면 DB에 저장하자
	@PostMapping("/cs/write")
	public String saveWrite(QnaDto dto) {
		qnaService.saveWrite(dto);
		return "redirect:/cs/qnaList";
	}
	
	//qnaDetail페이지로 이동
	@GetMapping("/cs/qnaDetail/{bno}")
	public String qnaDetail(@PathVariable long bno, Model model) {
		QnaDto detail = qnaService.getDetail(bno);
		model.addAttribute("detail", detail);
		return "/cs/qnaDetail";
	}
	
	//수정버튼 누르면 저장
	@PutMapping("/cs/qnaList/{bno}")
	public String updateDetail(@PathVariable long bno, QnaUpdateDto dto) {
		qnaService.updateDetail(dto);
		return "redirect:/cs/qnaDetail/"+bno;
	}
	
	//삭제버튼
	@DeleteMapping("/cs/qnaList/{bno}")
	public String deleteDetail(@PathVariable long bno) {
		System.out.println("delete bno 확인"+bno);
		qnaService.delete(bno);
		return "redirect:/cs/qnaList";
	}
		

}
