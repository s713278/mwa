package net.mwa.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.mwa.api.vo.MemberRegRequest;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddOfflinePaymentRequest extends MemberRegRequest{

	@ApiModelProperty(name="feeId" ,example="10002")
	private Long feeId;
	
	@ApiModelProperty(name="amount" ,example="1000")
	private Double amount;
	
	@ApiModelProperty(name="collectedBy" ,example="Nageswar B")
	private String collectedBy;
	
	@ApiModelProperty(name="mobileNo" ,example="9912149080")
	private String mobileNo;
	
	@ApiModelProperty(name="paidDate" ,example="12-12-2014 02:30:00")
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date paidDate;
	
	@ApiModelProperty(name="paymentMode",required=true,example="CASH",allowableValues="CASH,ONLINE,CHEQUE")
	private String paymentMode;
	
	@ApiModelProperty(name="transactionId")
	private String transactionId;

	@ApiModelProperty(name="receiptNo",required=true,example="1819")
	private String receiptNo;
	
	@ApiModelProperty(name="chequeNo",required=true,example="ICICI-1819")
	private String chequeNo;
	
	@ApiModelProperty(name="paidBy",required=true,example="Self")
	private String paidBy;

	@Override
	public String toString() {
		return "AddOfflinePaymentRequest [feeId=" + feeId + ", amount=" + amount + ", paidDate=" + paidDate
				+ ", paymentMode=" + paymentMode + ", receiptNo=" + receiptNo + ", chequeNo=" + chequeNo + "]";
	}
	
	
	
	
}
