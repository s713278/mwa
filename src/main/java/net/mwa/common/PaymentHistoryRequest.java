package net.mwa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentHistoryRequest extends APICommonRequest{

	@ApiModelProperty(name="memberId" ,example="1")
	private Long memberId;
	
	@ApiModelProperty(name="feeId" ,example="1")
	private Long feeId;
}
