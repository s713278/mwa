package net.mwa.admin.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value="/")
	public String getHomePage(){
		System.out.println("Home page loaded");
		return "index";
	}
}
