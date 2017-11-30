package net.mwa.member.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mwa.common.APICommonResponse;
import net.mwa.common.PaymentRequest;
import net.mwa.common.PaymentResponse;
import net.mwa.service.PaymentService;
import net.mwa.vo.PaymentDetailsVO;

@Controller("api/v1/payment")
public class PaymentController {

	private static Logger logger = Logger.getLogger(PaymentController.class.getName());

	
	@Autowired
	private PaymentService paymentService;


	@GetMapping(value = "/allPayments")
	public @ResponseBody Iterable<PaymentDetailsVO> listAllMembers() {
		return paymentService.listAllPayments();
	}

	
	
	@PostMapping(value = "/payFee")
	public @ResponseBody APICommonResponse payAmount(@RequestBody PaymentRequest request) {
		PaymentResponse response = (PaymentResponse) paymentService.payAmount(request);
		return response;
	}
}
