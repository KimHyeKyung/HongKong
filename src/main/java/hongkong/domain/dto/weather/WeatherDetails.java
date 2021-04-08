package hongkong.domain.dto.weather;

import lombok.Data;

@Data
public class WeatherDetails {

	int id;
	String main;
	String description;
	String icon;
}
