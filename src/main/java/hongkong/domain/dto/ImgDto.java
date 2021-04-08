package hongkong.domain.dto;

import hongkong.domain.entity.Image;
import hongkong.domain.entity.ImageFile;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImgDto {

	long no;
	String subject;
	String contents;
	String creatorId;
	
	ImgFileDto img;





	public ImgDto(Image img) {
		this.no = img.getNo();
		this.subject = img.getSubject();
		this.contents = img.getContents();
		this.creatorId = img.getCreatorId();
		
		ImageFile imFile = img.getImg();
		this.img = new ImgFileDto( imFile );
	}
	
	
}
