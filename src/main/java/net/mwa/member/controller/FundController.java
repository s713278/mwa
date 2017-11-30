package net.mwa.member.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mwa.service.FeeService;
import net.mwa.vo.FeeVO;

@Controller("api/v1/fee")
public class FundController {

	private static Logger logger = Logger.getLogger(FundController.class.getName());

	@Autowired
	private FeeService fundService ; 
	
	@PostMapping("/add")
	public @ResponseBody Long addFund(@RequestBody FeeVO fundVO){
		return fundService.save(fundVO);
	}
	
	@PostMapping("/update")
	public @ResponseBody Long updateFund(@RequestBody FeeVO fundVO){
		return fundService.save(fundVO);
	}
	
	@GetMapping(value="/list")
	public  @ResponseBody Iterable<FeeVO> listAllFunds(){
		return fundService.listAllFunds();
	}
}
