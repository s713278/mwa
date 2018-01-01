package net.mwa.admin.contoller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.mwa.common.AddOfflinePaymentRequest;
import net.mwa.feed.FeedFileParser;
import net.mwa.feed.MemberDetailsLineVO;
import net.mwa.feed.PaymentLineVO;

@Controller("api/v1/feed")

@Api(value="FileProcessorControler")
public class FileProcessorControler {

	private static final Logger logger = Logger.getLogger(FileProcessorControler.class.getName());

	@Autowired
	@Qualifier("MembersFeedFileProcessor")
	private FeedFileParser<MemberDetailsLineVO> membersFeedFileProcessor ;
	
	
	@Autowired
	@Qualifier("PaymentsFeedFileProcessor")
	private FeedFileParser<PaymentLineVO> paymentsFeedFileProcessor ; 
	
	@Autowired
	@Qualifier("MWADataFeedProcessor")
	private FeedFileParser<AddOfflinePaymentRequest> dataFeedProcessor;
	
	@PutMapping("/processMembersDetails")
	public @ResponseBody Object processMembersDetailsFile(){
		return membersFeedFileProcessor.processFile();
	}
	
	@PutMapping("/processPaymentDetails")
	public @ResponseBody Object processPaymentDetailsFile(){
		return paymentsFeedFileProcessor.processFile();
	}
	
	@PutMapping("/processDataFeedFile")
	public @ResponseBody Object processDataFeedFile(){
		return dataFeedProcessor.processFile();
	}
}
