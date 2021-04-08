package hongkong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExploreController {

	@GetMapping("/community/explore/attraction/attraction")
	public String attraction() {
		return "/community/explore/attraction/attraction";
	}
	
	@GetMapping("/community/explore/attraction/disneyLand")
	public String disneyLand() {
		return "/community/explore/attraction/disneyLand";
	}
	
	@GetMapping("/community/explore/attraction/symphonyOfLights")
	public String symphonyOfLights() {
		return "/community/explore/attraction/symphonyOfLights";
	}
	
	@GetMapping("/community/explore/attraction/family")
	public String family() {
		return "/community/explore/attraction/family";
	}
	
	@GetMapping("/community/explore/attraction/budget")
	public String budget() {
		return "/community/explore/attraction/budget";
	}
	
	//////////////////////////////////////////////
	@GetMapping("/community/explore/food/food")
	public String food() {
		return "/community/explore/food/food";
	}
	
	@GetMapping("/community/explore/food/coffee")
	public String coffee() {
		return "/community/explore/food/coffee";
	}
}
