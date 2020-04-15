/**
 * 
 */
package com.poc.document.api.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dhanunjaya.potteti
 *
 */
@Component
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "status", "statusCode", "message","data" })
public class APIResponse {
	
	private boolean status;
	private String statusCode;
	private String message;
	private Object data;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
