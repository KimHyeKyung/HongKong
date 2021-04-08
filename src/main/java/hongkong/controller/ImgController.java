package hongkong.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hongkong.domain.dto.ImgDto;
import hongkong.domain.dto.ImgRequestDto;
import hongkong.service.ImgService;

@Controller
public class ImgController {

	@Autowired
	ImgService service;
	
	//photoList에 DB에 등록한 데이터 읽어오기 --> read
	@GetMapping("/community/photo/photoList")
	public String list(Model model) {
		
		//데이터 읽어오기
		List<ImgDto>  list = service.getListAll(); //Img폴더가 메인폴더
		model.addAttribute("list",list);
		
		return "/community/photo/photoList";
	}
	
	
	//photo의 photoWrite페이지 보여주세요
	@GetMapping("/community/photo/photoWrite")
	public String goPhotoWrite() {
		  return "/community/photo/photoWrite"; 
	}
	
	//이미지 등록
	//MultipartFile로 받으면 파일만 받아지는거
	//parts를 사용하면 제목 내용 파일 한번에 받아짐
	//하지만 dto를 사용하면 되니깐 parts보다는 MultipartFile로 사용하자
	//--> 파일은 MultipartFile로 제목내용은 dto로 받자
	@PostMapping("/community/photo/photoWrite")
	public String write(MultipartFile file, ImgRequestDto dto) throws IOException {
		service.uploadAndSave(file, dto);
		return"redirect:/community/photo/photoList";
	}
	
	//이미지 업로드 ajax에 넣은 주소
	//복붙해서 다른곳에서 쓰라ㅏ아아
	//이미지 업로드하면 서버에 전송완료!
	@ResponseBody
	@PostMapping("/community/photo/preView")
	public void preView(MultipartFile file, String temp) throws IOException { // ajax에 "file"로 넣은 key값 파라미터에 넣어준다
		//System.out.println(file.getOriginalFilename());
		////파일이 넘어왔으니 임시파일에 넣어야한다.////
		System.out.println("temp :"+temp);
		//최초 temp=""
		//다음 선택할때 이전에 선택된 temp의 값이 나온다
		service.uploadTemp(file,temp); //서비스한테 일 시키자
	}

	
	//photo의 photoDetail페이지로 가면서 선택된 데이터 보여주세요
	@GetMapping("/community/photo/photoDetail/{no}")
	public String detail(@PathVariable long no, Model model) { //Model model: 정보 가져가야해서
		
		ImgDto detail = service.getDetail(no);
		model.addAttribute("detail",detail);
		
		return "/community/photo/photoDetail";
	}
	
	
	//photo의 photoDetail페이지에서 수정버튼 누르면 DB에 수정된 내용이 반영되게 해주세요
	@PostMapping("/community/photo/photoEdit")
	public String SaveAndUpdate(ImgDto dto) {
		service.update(dto);
		return "redirect:/community/photo/photoDetail/"+dto.getNo();
	}
	
	
	//delete
	@GetMapping("/community/photo/delete/{no}")
	public String delete(@PathVariable long no) {
		service.delete(no);
		return "redirect:/community/photo/photoList";
	}
}
