package net.mwa.vo;

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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="fee_details")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "lastUpdate"}, allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeVO {

	@Id
	@GeneratedValue
	private Long id;
	
	@ApiModelProperty(name="fundName", example="CC Camera's Fund")
	@NotBlank
	private String fundName;
	
	private String description;
	
	@Column(name = "AMOUNT")
	@ApiModelProperty(name="amount", example="1000")
	private double amount;
	
	@Column(name = "FUND_TYPE")
	private String fundType;//MONTHLY ONCE|YEARLY ONCE|ONLY ONE TIME
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "EXPIRE_DATE")
	private Date expireDate;
	
	@Column(name = "TERMS_AND_CONDITAIONS")
	private String termsAndConditaions;
	
	@Column(name = "IS_ACTIVE")
	private boolean active=Boolean.TRUE;
	
	@Column(name = "BANK_DETAILS")
	private String bankDetails;
	
	@Column(name = "IS_EXPIRED")
	private boolean expired=false;
	
	@OneToOne
	private CategoryVO category;
	
	@Column(name = "FEE_BY_FAMILY")
	private boolean feebyFamily=false; 
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;
}
