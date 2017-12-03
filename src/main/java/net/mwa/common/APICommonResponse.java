package net.mwa.common;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APICommonResponse {

	private String authToken;
	
	@ApiModelProperty(value = "true|false", required = true, hidden = true)
	private boolean success;
	
	private String errorCode;
	
	private String userMessage;
	
	private String developerMessage;
	
	private Long timeStamp = Calendar.getInstance().getTimeInMillis();
	
}
