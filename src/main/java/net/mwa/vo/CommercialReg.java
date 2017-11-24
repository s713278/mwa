package net.mwa.vo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COMMERCIAL_REG")
@DiscriminatorValue("COMMERCIAL")
public class CommercialReg extends MemberDetailsVO {

	private String businessName;
	
	private String typeOfBusiness;
	
}
