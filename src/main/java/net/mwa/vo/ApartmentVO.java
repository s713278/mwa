package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "APARTMENT_DETAILS")
@DiscriminatorValue("APARTMENT")
public class ApartmentVO extends UserDetailsVO {

	private String aprtmentName;
	
	@Column(name="PRESIDENT_NAME")
	private String presidentName;
	
	@Column(name="PRESEDENT_MOBILE_NO")
	private String presedentMobileNo;
	
	@Column(name="SECRETERY_NAME")
	private String secreteryName;
	
	@Column(name="SECRETERY_MOBILE_NO")
	private String secreteryMobileNo;
	
	@Column(name="APARTMENTE_MAIL")
	private String apartmentEmail;
	
}
