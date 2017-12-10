package net.mwa.admin.contoller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.mwa.feed.FileProcessor;

@Controller("api/v1/feed")

@Api(value="FileProcessorControler" ,description ="CSV/XLS file processing")
public class FileProcessorControler {

	private static final Logger logger = Logger.getLogger(FileProcessorControler.class.getName());

	@Autowired
	private FileProcessor fileProcessor ; 
	
	@PutMapping("/processMembersDetails")
	public @ResponseBody Object processMembersDetailsFile(){
		return fileProcessor.processFile();
	}
	
	@PutMapping("/processPaymentDetails")
	public @ResponseBody Object processPaymentDetailsFile(){
		return fileProcessor.processPaymentFile();
	}
}
