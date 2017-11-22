package net.mwa.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PAYMENT_DETAILS")
public class PaymentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2909982706079178546L;

	private Date paidDate;
	
	private double paidAmount;
	
	private String paidBy;
	
	private String paidTo;
	
	private FundVO fundVO;
	
}
