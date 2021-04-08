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
	
	@ResponseBody
	@GetMapping("/weather/result")
	public ModelAndView weather(ModelAndView mv) {
		OpenWeather result=weatherService.getWeatherData();
		mv.addObject("main", result.getMain());
		mv.addObject("weather", result.getWeather());
		mv.setViewName("/weather");
		return mv;
		//if(result==null)return;
	}
	
}
