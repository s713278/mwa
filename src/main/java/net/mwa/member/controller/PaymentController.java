package net.mwa.member.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mwa.common.APICommonResponse;
import net.mwa.common.PaymentDueResponse;
import net.mwa.common.PaymentHistoryResponse;
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

	
	
	@PostMapping(value = "/payDue")
	public @ResponseBody APICommonResponse payAmount(@RequestBody PaymentRequest request) {
		PaymentResponse response = (PaymentResponse) paymentService.payAmount(request);
		return response;
	}
	
	
	@PostMapping(value = "/paymentHistoryByMemberId/{membId}")
	public @ResponseBody APICommonResponse getPaymentHistoryByMemberId(@PathVariable Long membId) {
		PaymentHistoryResponse response = (PaymentHistoryResponse) paymentService.getPaymentHistoryByMemberId(membId);
		return response;
	}
	
	@PostMapping(value = "/getPaymentHistoryByFeeId/{feeId}")
	public @ResponseBody APICommonResponse getPaymentHistoryByFeeId(@PathVariable Long feeId) {
		PaymentHistoryResponse response = (PaymentHistoryResponse) paymentService.getPaymentHistoryByFeeId(feeId);
		return response;
	}
	
	@PostMapping(value = "/getDuesByMemberId/{membId}")
	public @ResponseBody APICommonResponse PaymentDueResponse(@PathVariable Long membId) {
		PaymentDueResponse response = (PaymentDueResponse) paymentService.getPaymentDuesByMemberId(membId);
		return response;
	}
	
	
	
}
