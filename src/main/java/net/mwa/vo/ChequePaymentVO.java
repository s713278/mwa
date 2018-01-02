package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cheque_payment_details")
@DiscriminatorValue("CHEQUE")
public class ChequePaymentVO extends PaymentDetailsVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7907866282762151316L;

	@Column(name="CHEQUE_NO")
	private String chequeNo;
	
	@Column(name="BANK_NAME")
	private String bankName;
	
	@Column(name="STATUS") //CLEARED/NOT CLEARED
	private String status;
	
}
