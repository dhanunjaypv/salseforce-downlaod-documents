/**
 * 
 */
package com.poc.document.api.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;

/**
 * @author dhanunjaya.potteti
 *
 */
@EqualsAndHashCode(callSuper = false)
@Component
public class UserInfo implements Serializable {

	private String orgId;
	private String sessionId;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
