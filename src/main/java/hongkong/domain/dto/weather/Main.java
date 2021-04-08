package hongkong.domain.dto.weather;

import lombok.Data;

@Data
public class Main {

	double temp;
	double feels_like;
	double temp_min;
	double temp_max;
	double pressure;
	double humidity;
}
