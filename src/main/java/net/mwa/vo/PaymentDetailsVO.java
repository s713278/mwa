package net.mwa.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="MEMBER_PAYMENT_DETAILS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"paidDate", "lastUpdate"}, allowGetters = true)
public class PaymentDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2909982706079178546L;

	@Id
	@GeneratedValue
	private Long id;
	
	private double paidAmount;
	
	private String paidBy;
	
	private String collectedBy;
	
	private String note;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date paidDate;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;
	
	
	@OneToOne
	private FeeVO feeVO;
	
	@OneToOne
	private MemberDetailsVO member;
	
}
