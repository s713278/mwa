package net.mwa.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@Table(name="payment_details")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"paidDate", "lastUpdate"}, allowGetters = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class PaymentDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2909982706079178546L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
//	@TableGenerator(name = "table", allocationSize = 10000)
	private Long id;
	
	@Column(name="PAID_AMOUNT")
	private double paidAmount;
	
	private String note;
	
	@Column(name="PAID_DATE",nullable = false, updatable = false)
    private Date paidDate;
	
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;
	
	@Column(name="RECEIPT_NO")
	private String receiptNo;
	
	@Transient
	@Column(name="TYPE")
	private String type;
	
	@OneToOne
	private FeeVO fee;
	
	@OneToOne
	private UserDetailsVO member;
	
}
