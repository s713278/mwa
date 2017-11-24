package net.mwa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse extends APICommonResponse {

	private Long referenceID;
}
