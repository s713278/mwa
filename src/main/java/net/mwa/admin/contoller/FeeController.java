package net.mwa.admin.contoller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.mwa.service.FeeService;
import net.mwa.vo.FeeVO;

@Controller("api/v1/fee")

@Api(value="FeeController" ,description ="Operations pertaining to MWA fee")
public class FeeController {

	private static Logger logger = Logger.getLogger(FeeController.class.getName());

	@Autowired
	private FeeService fundService ; 
	
	@PostMapping("/addFee")
	public @ResponseBody Long addFund(@Valid @RequestBody FeeVO fundVO){
		return fundService.save(fundVO);
	}
	
	@PostMapping("/updateFee")
	public @ResponseBody Long updateFund(@RequestBody FeeVO fundVO){
		return fundService.save(fundVO);
	}
	
	@GetMapping(value="/listAllFees")
	public  @ResponseBody Iterable<FeeVO> listAllFunds(){
		return fundService.listAllFunds();
	}
}
