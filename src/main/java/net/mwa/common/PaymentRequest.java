package net.mwa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequest extends APICommonRequest{

	@ApiModelProperty(name="memberId" ,example="1")
	private Long memberId;
	
	@ApiModelProperty(name="feeId" ,example="1")
	private Long feeId;
	
	@ApiModelProperty(name="amount" ,example="1000")
	private Double amount;
	
	@ApiModelProperty(name="paidBy" ,example="Swamy K")
	private String paidBy;
	
	@ApiModelProperty(name="collectedBy" ,example="Nageswar B")
	private String collectedBy;
	
	@ApiModelProperty(name="mobileNo" ,example="9912149080")
	private String mobileNo;
}
