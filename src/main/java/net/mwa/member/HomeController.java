package net.mwa.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mwa.service.MemberRegService;
import net.mwa.vo.MemberReg;

@Controller

public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class.getName());

	@Autowired
	private MemberRegService memberRegService;

	@GetMapping(value="/")
	public String homePage(){
		System.out.println("Loading Home Page");
		return "index";
	}
	
	@GetMapping(value="/home")
	public String home(){
		return "home";
	}

	@PostMapping(value="/sum")
	public @ResponseBody int getTotal(int a ,int b){
	
		return a+b;
	}
	
	
	@PostMapping("addMember")
	public @ResponseBody Long addMember(@RequestBody MemberReg memberReg){
		return memberRegService.save(memberReg);
	}
	
	@GetMapping(value="/listAllMembers")
	public  @ResponseBody Iterable<MemberReg> listAllMembers(){
		return memberRegService.listAllMemebrs();
	}

}
