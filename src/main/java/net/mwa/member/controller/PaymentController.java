package net.mwa.member.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mwa.common.APICommonResponse;
import net.mwa.common.AddOfflinePaymentRequest;
import net.mwa.common.AddOfflinePaymentResponse;
import net.mwa.common.PaymentDueResponse;
import net.mwa.common.PaymentRequest;
import net.mwa.common.PaymentResponse;
import net.mwa.service.PaymentService;

@Controller("api/v1/payment")
@Api(value="Operations pertaining to member payment services")
public class PaymentController {

	private static Logger logger = Logger.getLogger(PaymentController.class.getName());

	
	@Autowired
	private PaymentService paymentService;


	@PostMapping(value = "/getDueAmount/{membId}")
	public @ResponseBody APICommonResponse getDueAmount(@PathVariable Long membId) {
		PaymentDueResponse response = (PaymentDueResponse) paymentService.getPaymentDuesByMemberId(membId);
		return response;
	}
	
	
	@ApiOperation(value = "Pays due amount",
    notes = "MemberId,FeeId and amount needs to be passed",
    response = APICommonResponse.class,
    responseContainer = "PaymentResponse")
	@PostMapping(value = "/payDueAmout")
	public @ResponseBody APICommonResponse payDueAmount(@RequestBody PaymentRequest request) {
		PaymentResponse response = (PaymentResponse) paymentService.payAmount(request);
		return response;
	}
	
	@ApiOperation(value = "Add offline payment",
    notes = "PlotNo,FeeId,Amount,MobileNo,Email,Category and PaymentMode needs to be passed",
    response = AddOfflinePaymentResponse.class,
    responseContainer = "AddOfflinePaymentResponse")
	@PostMapping(value = "/addOffLinePayment")
	public @ResponseBody APICommonResponse addOffLinePayment(@RequestBody AddOfflinePaymentRequest request) {
		AddOfflinePaymentResponse response = (AddOfflinePaymentResponse) paymentService.addOffLinePayment(request);
		return response;
	}
	
	
}
