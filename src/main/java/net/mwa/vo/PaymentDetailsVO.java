package net.mwa.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="MEMBER_PAYMENT_DETAILS")
public class PaymentDetailsVO implements Serializable {

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
	
	@OneToOne
	private FeeVO fundVO;
	
	@OneToOne
	private MemberDetailsVO member;
	
}
