package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CASH_PAYMENT_DETAILS")
@DiscriminatorValue("CASH")
public class CashPaymentVO extends PaymentDetailsVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6067912245095893167L;

	@Column(name="COLLECTED_BY")
	private String collectedBy;
	
	@Column(name="PAID_BY")
	private String paidBy;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
}
