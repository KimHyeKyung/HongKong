package hongkong.domain.dto;

import hongkong.domain.entity.Image;
import hongkong.domain.entity.ImageFile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImgRequestDto {
	
	private long no;
	private String subject;
	private String contents;
	private String temp;
	
	public Image toEntity(ImageFile fileEntity){
		//return Img.builder().build(); == new Img();
		return Image.builder()
					.subject(subject)
					.contents(contents)
					.img(fileEntity)
					.build(); 
		// new Img(subject, contents);
	}
}