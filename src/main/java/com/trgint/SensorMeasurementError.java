package com.trgint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorMeasurementError {

	@JsonProperty("error_code")
	private String errorCode;
	@JsonProperty("error_message")
	private String errorMessage;
	
	
	public SensorMeasurementError(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
