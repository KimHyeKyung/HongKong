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

		    RestTemplate restTemplate = new RestTemplate();
		    OpenWeather response = restTemplate.getForObject(urlBuilder.toString(), OpenWeather.class);

		    return response;
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		return null;
	}

}
