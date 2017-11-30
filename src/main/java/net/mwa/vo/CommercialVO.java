package net.mwa.vo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COMMERCIAL_DETAILS")
@DiscriminatorValue("COMMERCIAL")
public class CommercialVO extends MemberDetailsVO {

	private String businessName;
	
	private String typeOfBusiness;
	
}
