package com.poc.document.api.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Component
public class DocumentRequestDto implements Serializable {

	private static final long serialVersionUID = -3359575242802190772L;

	private String username;
	private String password;
	private String faileName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFaileName() {
		return faileName;
	}
	public void setFaileName(String faileName) {
		this.faileName = faileName;
	}
	
}
