package net.mwa.admin.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping(value="/")
	public String getHomePage(){
		System.out.println("Home page loaded");
		return "index";
	}
	
	@GetMapping(value="/admin")
	public String getAdmin(){
		System.out.println("getAdmin page loaded");
		return "/admin/admin";
	}
	
	@GetMapping(value="/user")
	public String getUserPage(){
		System.out.println("getUserPage page loaded");
		return "/user/user"; 
	}
	
	@GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
	
	@GetMapping("/404")
    public String error404() {
        return "/error/404";
    }
	
	@GetMapping(value="/login")
	public String getLogin(){
		System.out.println("getLogin page loaded");
		return "/user/login";
	}	
	
	@GetMapping(value="/logout")
	public String getLogOut(){
		System.out.println("getLogOut page loaded");
		return "/user/login";
	}	
}
