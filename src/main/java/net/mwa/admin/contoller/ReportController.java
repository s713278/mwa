package net.mwa.admin.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mwa.common.APICommonResponse;
import net.mwa.common.PaymentHistoryRequest;
import net.mwa.common.PaymentHistoryResponse;
import net.mwa.service.PaymentService;
import net.mwa.vo.PaymentDetailsVO;

@RestController("api/v1/reports")
@Api(value="ReportController")
public class ReportController {

	@Autowired
	private PaymentService paymentService;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
	@ApiOperation(value = "Gets payment history by the member id.")
	@PostMapping(value = "/paymentsByMemberId/{membId}")
	public @ResponseBody APICommonResponse getPaymentHistoryByMemberId(@PathVariable Long membId) {
		PaymentHistoryResponse response = (PaymentHistoryResponse) paymentService.getPaymentHistoryByMemberId(membId);
		return response;
	}
	
	@ApiOperation(value = "Gets payment history by the fee id.")
	@PostMapping(value = "/getPaymentsByFeeId/{feeId}")
	public @ResponseBody APICommonResponse getPaymentHistoryByFeeId(@PathVariable Long feeId) {
		PaymentHistoryResponse response = (PaymentHistoryResponse) paymentService.getPaymentHistoryByFeeId(feeId);
		return response;
	}
	
	@ApiOperation(value = "Gets all payment made by the residents.")
	@GetMapping(value = "/allPayments")
	public @ResponseBody Iterable<PaymentDetailsVO> listAllPayments() {
		Iterable<PaymentDetailsVO> list = paymentService.listAllPayments();
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("In side listAllPayments() ");
		}
		return list;
	}
	
	@ApiOperation(value = "Gets all payment based on memberId and feeId.")
	@PostMapping(value = "/paymentsByMemberIdANDFeeId")
	public @ResponseBody PaymentHistoryResponse paymentsByMemberIdANDFeeId(final PaymentHistoryRequest historyRequest) {
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("In side paymentsByMemberIdANDFeeId() ");
		}
		return paymentService.getPaymentsByMemberIdANDFeeId(historyRequest);
	}
}
