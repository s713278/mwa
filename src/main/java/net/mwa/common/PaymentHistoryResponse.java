package net.mwa.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import net.mwa.vo.PaymentDetailsVO;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentHistoryResponse extends APICommonResponse {
	
	int count;

	List<PaymentDetailsVO> paymentHistory;
}
