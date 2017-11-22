package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MEMBER_REG")
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorValue("INDEPENDENT")
@Data
public class MemberReg {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="OWNER_FIRST_NAME")
	private String ownerFirstName;
	
	@Column(name="OWNER_LAST_NAME")
	private String ownerLastName;
	
	@Column(name="HOUSE_NO")
	private String plotNo;
	
	@Column(name="EMAIL_ID")
	private String emailID;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="NO_OF_FAMILIES")
	private int noOfFamilies;
	
	@Column(name="IS_ACTIVE")
	private boolean active;
	
	
	private PaymentDetails paymentDetails;
	
}
