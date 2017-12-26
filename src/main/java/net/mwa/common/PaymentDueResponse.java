package net.mwa.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import net.mwa.vo.FeeVO;
import net.mwa.vo.PaymentDetailsVO;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDueResponse extends APICommonResponse {
	
	int noOfPaymentDues;
	int noOfPaymentsCompleted;
	List<PaymentDetailsVO> paymentHistory;
	List<FeeVO> payemntDues;
}
