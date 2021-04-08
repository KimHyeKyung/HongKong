package hongkong.domain.dto.weather;

import java.util.List;

import lombok.Data;


@Data
public class OpenWeather {
	
	private Coord coord;
	private List<Weather> weather;
	private String base;
	private Main main;
	private long dt; //데이터 시간계산
	private Sys sys;
	private int timezone;
	private int id;
	private String name;
	private int cod;
	

}
