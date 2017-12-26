package net.mwa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchMemberRequest extends APICommonRequest{

	@ApiModelProperty(name="memberId" ,example="120")
	private Long memberId;
	
	@ApiModelProperty(name="mobileNo" ,example="9912536982")
	private String mobileNo;
	
	@ApiModelProperty(name="plotNo" ,example="MIG-973/L")
	private String plotNo;
	
	@ApiModelProperty(name="plotNo" ,example="Swamy")
	private String firstName;
	
	@ApiModelProperty(name="lastName" ,example="Kunta")
	private String lastName;
}
