package net.mwa.feed;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PaymentLineVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4177657047801350213L;

	/**
	 * 
	 */

	private String receiptNo;
	
	private double paidAmount;
	
	private String paidBy;
	
	private String note;
	
    private Date paidDate;
	
	private Date lastUpdate;
	
	private String plotNo;
	
	private String category;
	
	private int noOfFamilies;
	
	private String mobileNo;
	
	private String modeOfPayment;
	
	private String chequeNo;
	
	private Long feeId;
	
}
