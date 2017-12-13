package net.mwa.admin.contoller;

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
@Api(value="ReportController",description="Operations pertaining to MWA reports")
public class ReportController {

	@Autowired
	private PaymentService paymentService;
	
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
	
	@ApiOperation(value = "Gets all payment made by the members.")
	@GetMapping(value = "/allPayments")
	public @ResponseBody Iterable<PaymentDetailsVO> listAllPayments() {
		return paymentService.listAllPayments();
	}
	
	@ApiOperation(value = "Gets all payment based on memberId and feeId.")
	@PostMapping(value = "/paymentsByMemberIdANDFeeId")
	public @ResponseBody PaymentHistoryResponse paymentsByMemberIdANDFeeId(final PaymentHistoryRequest historyRequest) {
		return paymentService.getPaymentsByMemberIdANDFeeId(historyRequest);
	}
}
