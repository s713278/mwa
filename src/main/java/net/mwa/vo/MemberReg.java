package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_REG")
public class MemberReg {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="OWNER_FIRST_NAME")
	private String ownerFirstName;
	
	private String ownerLastName;
	
	private String plotNo;
	
	private String emailID;
	
	private String mobileNo;
	
	private int noOfFamilies;
	
	private String type;
	
	
}
