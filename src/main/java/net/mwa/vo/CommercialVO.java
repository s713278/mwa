package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMERCIAL_DETAILS")
@DiscriminatorValue("COMMERCIAL")
public class CommercialVO extends MemberDetailsVO {

	@Column(name="BUSINESS_NAME")
	private String businessName;
	
	@Column(name="TYPE_OF_BUSINESS")
	private String typeOfBusiness;
	
	@Column(name="BUSINESS_LOGO_PATH")
	private String businessLogoPath;
	
}
