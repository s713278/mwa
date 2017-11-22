package net.mwa.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="PAYMENT_DETAILS")
public class PaymentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2909982706079178546L;

	@Id
	@GeneratedValue
	private Long id;
	
	private Date paidDate;
	
	private double paidAmount;
	
	private String paidBy;
	
	private String collectedBy;
	
	private String note;
	
	@Transient
	private FundVO fundVO;
	
	@Transient
	private MemberReg member;
	
}
