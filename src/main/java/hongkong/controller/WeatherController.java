package hongkong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hongkong.domain.dto.weather.OpenWeather;
import hongkong.utils.WeatherService;

@Controller
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	

	//한국
	@ResponseBody
	@GetMapping("/weather/KoResult")
	public ModelAndView KoWeather(ModelAndView mv) {
		OpenWeather result=weatherService.getKoWeatherData();
		mv.addObject("komain", result.getMain());
		mv.addObject("koweather", result.getWeather());
		mv.setViewName("/weather");
		return mv;
		//if(result==null)return;
	}
	
	//홍콩
	@ResponseBody
	@GetMapping("/weather/HkResult")
	public ModelAndView HkWeather(ModelAndView mv) {
		OpenWeather result=weatherService.getHkWeatherData();
		mv.addObject("hkmain", result.getMain());
		mv.addObject("hkweather", result.getWeather());
		mv.setViewName("/weather");
		return mv;
		//if(result==null)return;
	}
	
}
