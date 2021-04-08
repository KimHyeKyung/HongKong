package hongkong.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import hongkong.domain.dto.ImgDto;
import hongkong.domain.dto.ImgRequestDto;

public interface ImgService {

	void uploadAndSave(MultipartFile file, ImgRequestDto dto) throws IOException;

	void uploadTemp(MultipartFile file, String temp) throws IOException;

	List<ImgDto> getListAll();

	ImgDto getDetail(long no);

	void delete(long no);

	void update(ImgDto dto);

}
