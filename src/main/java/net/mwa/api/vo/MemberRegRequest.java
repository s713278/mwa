package net.mwa.api.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.mwa.common.APICommonRequest;

@Api("request")
@Data
public class MemberRegRequest extends APICommonRequest{

	@ApiModelProperty(name="firstName",example="Chetan",position=0)
	private String firstName;

	@ApiModelProperty(name="lastName",example="K",position=1)
	private String lastName;

	@ApiModelProperty(name="middleName",example="K",position=1)
	private String middleName;

	@ApiModelProperty(name="plotNo",example="MIG-973/L",position=2)
	private String plotNo;

	@ApiModelProperty(name="address1",example="Near RL City",position=3)
	private String address1;

	@ApiModelProperty(name="emailID",example="mwa.abcde@gmail.com",position=4)
	private String emailID;

	@ApiModelProperty(name="mobileNo",example="9848336109",position=5)
	private String mobileNo;

	@ApiModelProperty(name="noOfFamilies",example="2",position=6)
	private int noOfFamilies = 1;

	@ApiModelProperty(name="roadNo",example="Road No-27F",position=7)
	private String roadNo;

	private String password="Mwa!7890";

	private short active = 1;

	private long categoryId;
	
	@ApiModelProperty(name="apartmentName",example="Ananda nilayam",position=8)
	private String apartmentName;
	
	@ApiModelProperty(name="businessName",example="Jai Bhavani General Stores",position=9)
	private String businessName;

	@Override
	public String toString() {
		return "MemberRegRequest [plotNo=" + plotNo + ", mobileNo=" + mobileNo + ", noOfFamilies=" + noOfFamilies
				+ ", categoryId=" + categoryId + "]";
	}

	
}
