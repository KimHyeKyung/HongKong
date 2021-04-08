package hongkong.domain.dto;

import hongkong.domain.entity.ImageFile;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImgFileDto {

	long no;
	
	String orgName; //originalName
	String newName;
	long fileSize;
	String fileUrl;
	
	public ImgFileDto(ImageFile imgFile) {
		this.no = imgFile.getNo();
		this.orgName = imgFile.getOrgName();
		this.newName = imgFile.getNewName();
		this.fileSize = imgFile.getFileSize();
		this.fileUrl = imgFile.getFileUrl();
	}
}
