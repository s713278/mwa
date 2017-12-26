package net.mwa.feed;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberDetailsLineVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4177657047801350213L;

	/**
	 * 
	 */

	private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String apartmentName;
	
	private String plotNo;
	
	private String roadNo;
	
	private String category;
	
	private int noOfFamilies;
	
	private String mobileNo;
	
	private long categoryId;
	
	private String businesName;
	
	private String webSite;
	
	private String whatsApp;
	
}
