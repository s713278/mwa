package net.mwa.member;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class.getName());


	@GetMapping(value="/")
	public String homePage(){
		System.out.println("Loading Home Page");
		return "index";
	}
	
	@GetMapping(value="/home")
	public String home(){
		return "home";
	}

	@GetMapping(value="/sum")
	@PostMapping
	public @ResponseBody int getTotal(int a ,int b){
	
		return a+b;
	}
}
