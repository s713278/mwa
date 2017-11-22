package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "MEMBER_REG")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorValue("INDEPENDENT")
@Data
public class MemberReg {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	
	@ApiModelProperty(name="ownerFirstName",example="Chetan")
	@Column(name="OWNER_FIRST_NAME")
	private String ownerFirstName;
	
	@ApiModelProperty(name="ownerLastName",example="K")
	@Column(name="OWNER_LAST_NAME")
	private String ownerLastName;
	
	@ApiModelProperty(name="plotNo",example="MIG-973/L")
	@Column(name="HOUSE_NO")
	private String plotNo;
	
	@ApiModelProperty(name="emailID",example="mwa.abcde@gmail.com")
	@Column(name="EMAIL_ID")
	private String emailID;
	
	@ApiModelProperty(name="mobileNo",example="9848336109")
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@ApiModelProperty(name="noOfFamilies",example="2")
	@Column(name="NO_OF_FAMILIES")
	private int noOfFamilies;
	
	@Column(name="IS_ACTIVE")
	private boolean active;
}
