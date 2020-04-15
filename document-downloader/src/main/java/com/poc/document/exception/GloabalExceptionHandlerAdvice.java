package com.poc.document.exception;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.poc.document.api.model.EventResponse;

@ControllerAdvice
public class GloabalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MesserApAutomationException.class)
	public ResponseEntity<EventResponse> exceptionHandler(MesserApAutomationException ex) {
		return new ResponseEntity<>(ex.getErrorResponse(), HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		EventResponse error = new EventResponse();
		error.setStatus(false);
		error.setStatusCode("MC-0012");
		error.setMessage(ex.getBindingResult().toString());
		// return new ResponseEntity<>(error, HttpStatus.OK);
		return handleExceptionInternal(ex, error, headers, HttpStatus.OK, request);

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		EventResponse error = new EventResponse();
		error.setStatus(false);
		error.setStatusCode("MC-0012");

		if (mostSpecificCause != null) {
			String exceptionName = mostSpecificCause.getClass().getName();
			String message = mostSpecificCause.getLocalizedMessage();

			error.setMessage(message);

		} else {

			error.setMessage(ex.getMessage());
		}
		return new ResponseEntity(error, headers, status);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String msg = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		EventResponse error = new EventResponse();
		error.setStatus(false);
		error.setStatusCode("MC-0012");
		error.setMessage(msg);
		/*
		 * ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
		 * ex.getLocalizedMessage(), error);
		 */
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.OK);
	}

}