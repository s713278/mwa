package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_REG")
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorValue("INDEPENDENT")
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
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ownerFirstName
	 */
	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	/**
	 * @param ownerFirstName the ownerFirstName to set
	 */
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	/**
	 * @return the ownerLastName
	 */
	public String getOwnerLastName() {
		return ownerLastName;
	}

	/**
	 * @param ownerLastName the ownerLastName to set
	 */
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	/**
	 * @return the plotNo
	 */
	public String getPlotNo() {
		return plotNo;
	}

	/**
	 * @param plotNo the plotNo to set
	 */
	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}

	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the noOfFamilies
	 */
	public int getNoOfFamilies() {
		return noOfFamilies;
	}

	/**
	 * @param noOfFamilies the noOfFamilies to set
	 */
	public void setNoOfFamilies(int noOfFamilies) {
		this.noOfFamilies = noOfFamilies;
	}

}
