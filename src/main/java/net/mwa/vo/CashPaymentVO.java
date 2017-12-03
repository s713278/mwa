package net.mwa.vo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CASH_PAYMENT_DETAILS")
@DiscriminatorValue("CASHPAYMENT")
public class CashPaymentVO extends PaymentDetailsVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6067912245095893167L;

	private String collectedBy;
	
	private String paidBy;
	
	private String mobileNo;
	
	private String receiptNo;
}
