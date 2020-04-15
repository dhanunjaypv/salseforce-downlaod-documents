package com.poc.document.exception;

import com.poc.document.api.model.EventResponse;

public class MesserApAutomationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final EventResponse errorResponse;

	public MesserApAutomationException(String errorCode, String errorMessage) {
		errorResponse = new EventResponse();
		errorResponse.setStatus(false);
		errorResponse.setStatusCode(errorCode);
		errorResponse.setMessage(errorMessage);
	}

	public EventResponse getErrorResponse() {
		return errorResponse;
	}

}
