package com.poc.document.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.poc.document.api.model.EventResponse;

/**
 * @author dhanunjaya.potteti
 *
 */
@Service
public interface DocumentClient {

	EventResponse post(String serviceName, HttpHeaders prepareHttpHeaders, Object requestObj);

	EventResponse get(String serviceName, HttpHeaders prepareHttpHeaders);

	String invokePost(String serviceName, HttpHeaders prepareHttpHeaders, Object requestObj);

}
