package net.mwa.member.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mwa.service.MemberRegService;
import net.mwa.vo.MemberReg;


@Controller("member")
public class MemberRegController {

	private static Logger logger = Logger.getLogger(MemberRegController.class.getName());

	@Autowired
	private MemberRegService memberRegService;
	
	@PostMapping(value="/sum")
	public @ResponseBody int getTotal(int a ,int b){
	
		return a+b;
	}
	
	
	@PostMapping("/addMember")
	public @ResponseBody Long addMember(@RequestBody MemberReg memberReg){
		return memberRegService.save(memberReg);
	}
	
	@PostMapping("/updateMember")
	public @ResponseBody Long updateMember(@RequestBody MemberReg memberReg){
		return memberRegService.save(memberReg);
	}
	
	@GetMapping(value="/listAllMembers")
	public  @ResponseBody Iterable<MemberReg> listAllMembers(){
		return memberRegService.listAllMemebrs();
	}
}
