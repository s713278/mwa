package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "online_payment_details")
@DiscriminatorValue("ONLINE")
public class OnlinePaymentVO extends PaymentDetailsVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1453893300331451482L;
	
	@Column(name="TRANSACTION_ID")
	private String transactionId;
	
	@Column(name="FROM_BANK")
	private String fromBank;
	
	@Column(name="VERIFIED_STATUS")
	private String verifiedStatus;
}
