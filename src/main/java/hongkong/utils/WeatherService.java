package hongkong.utils;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hongkong.domain.dto.weather.OpenWeather;

@Component
public class WeatherService {
	
	private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
	private final String apiKey = "ca86c98c4b8314b67a82180c5408420c"; // 발급받은 API key
	
	
	public OpenWeather getWeatherData() {
		StringBuilder urlBuilder = new StringBuilder(BASE_URL);
		try {
		    urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=Hongkong");
		    urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
		    urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
		    urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

		    
		    //RestTemplate
		    //간편하게 Rest 방식 api를 호출할 수 있는 spring 내장 클래스
		    //json , xml응답을 모두 받을 수 있다.
		    //여기에서는 json형식으로 응답을 받는다!
		    //리턴퇴는 데이터를 가져오기 위해서 RestTemplate객체를 이용했다
		    RestTemplate restTemplate = new RestTemplate();
		    
		    //getForObject: 주어진 URL 주소로 HTTP GET 메서드로 객체로 결과를 반환받는다
		    //getForObject를 통해 클래스 구조에 자동 매핑
		    OpenWeather response = restTemplate.getForObject(urlBuilder.toString(), OpenWeather.class);

		    return response;
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		return null;
	}

}
