package net.mwa.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COMMERCIAL_REG")
public class CommercialReg extends MemberReg {

	private String businessName;
	
	private String typeOfBusiness;
	
}
