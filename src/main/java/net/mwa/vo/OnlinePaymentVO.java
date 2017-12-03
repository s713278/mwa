package net.mwa.vo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ONLINE_PAYMENT_DETAILS")
@DiscriminatorValue("ONLINEPAYMENT")
public class OnlinePaymentVO extends PaymentDetailsVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1453893300331451482L;
	private String transactionId;
}
