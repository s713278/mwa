package net.mwa.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="FEE_DETAILS")
public class FeeVO {

	@Id
	@GeneratedValue
	private Long id;
	
	@ApiModelProperty(name="fundName", example="CC Camera's Fund")
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
	private boolean expired;
	
	
}
