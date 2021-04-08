package hongkong.domain.dto.weather;

import lombok.Data;

@Data
public class Sys {

	int type;
	int id;
	String country;
	long sunrise;
	long sunset;
}
