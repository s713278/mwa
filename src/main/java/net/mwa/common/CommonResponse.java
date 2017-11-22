package net.mwa.common;

import lombok.Data;

@Data
public class CommonResponse {

	private boolean success;
	
	private String errorCode;
	
	private String errorMessage;
	
	private String reasonCode ;
	
	private String reasonMessage;
	
}
