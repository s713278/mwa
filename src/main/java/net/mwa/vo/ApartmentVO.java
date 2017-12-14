package net.mwa.vo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "APARTMENT_DETAILS")
@DiscriminatorValue("APARTMENT")
public class ApartmentVO extends MemberDetailsVO {

	private String aprtmentName;
	
	private String presedentFirstName;
	
	private String presedentLastName;
	
	private String presedentMobileNo;
	
	private String secreteryFirstName;
	
	private String secreteryLastName;
	
	private String secreteryMobileNo;
	
	private String apartmentEmail;
	
}
