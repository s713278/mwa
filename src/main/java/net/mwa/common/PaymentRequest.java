package net.mwa.common;

import lombok.Data;

@Data
public class PaymentRequest extends APICommonRequest{

	private Long memberId;
	
	private Double amount;
}
