/**
 * 
 */
package com.poc.document.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.document.api.model.EventResponse;
import com.poc.document.config.PropertiesConfig;
import com.poc.document.service.DocumentClient;
import com.poc.document.util.DocumentUtil;
import com.poc.document.util.DocumnetConstants;

/**
 * @author dhanunjaya.potteti
 *
 */
@Service
public class DocumentClientImpl implements DocumentClient {
	private static final Logger logger = LoggerFactory.getLogger(DocumentClientImpl.class);

	@Autowired
	PropertiesConfig properties;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	DocumentUtil messerUtil;

	/*
	 * It will invoke external service with Request body & Return APIResponse
	 * 
	 * @Request:serviceName, it have specific service value
	 * 
	 * @Request:headers, it have specific company details
	 * 
	 * @Request:requestObj , it post request Body
	 * 
	 * @Return: externalServiceResponse, this response coming from external service
	 */
	@Override
	public EventResponse post(String serviceName, HttpHeaders headers, Object requestObj) {
		ObjectMapper objMapper = null;

		try {
			objMapper = new ObjectMapper();
			logger.info("External Service url#{} headers#{} Request#{}", serviceName, headers,objMapper.writeValueAsString(requestObj));
			ResponseEntity<String> response = restTemplate.exchange(serviceName, HttpMethod.POST,
					new HttpEntity<>(requestObj, headers), String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				logger.debug("Event API post() service successfully done");
				logger.info("External Service Response# {}", response.getBody());
				return messerUtil.prepareSuccessResponse(DocumnetConstants.SUCCESS_CD,
						DocumnetConstants.SUCCESS_MSG, response.getBody());

			}
		} catch (HttpStatusCodeException e) {
			logger.error("External Service Bad Response# {}", e.getResponseBodyAsString());
			return messerUtil.prepareSuccessResponse(DocumnetConstants.SUCCESS_CD,
					DocumnetConstants.SUCCESS_MSG, e.getResponseBodyAsString());

		} catch (Exception e) {
			logger.error("External Service Internal error# {}", e.getMessage());
			return messerUtil.prepareErrorResponse(DocumnetConstants.EXT_ERROR_CD,
					DocumnetConstants.EXT_ERROR_MSG);

		}
		return messerUtil.prepareErrorResponse(DocumnetConstants.EXT_ERROR_CD,
				DocumnetConstants.EXT_ERROR_MSG);

	}

	/*
	 * It will invoke external service & Return APIResponse
	 * 
	 * @Request:serviceName, it have specific service value
	 * 
	 * @Request:headers, it have specific company details
	 * 
	 * @Return: externalServiceResponse, this response coming from external service
	 */
	@Override
	public EventResponse get(String serviceName, HttpHeaders headers) {
		logger.info("External serviceName# {}", serviceName);

		RestTemplate restTemplate = new RestTemplate();

		try {
			ResponseEntity<EventResponse> response = restTemplate.exchange(serviceName, HttpMethod.GET,
					new HttpEntity<>(headers), EventResponse.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				logger.debug("Event API get() service successfully done");
				logger.info("External Service Response# {}", response.getBody());
				return messerUtil.prepareSuccessResponse(DocumnetConstants.SUCCESS_CD,
						DocumnetConstants.SUCCESS_MSG, response.getBody());

			}
		} catch (HttpStatusCodeException e) {
			logger.error("External Service Bad Response# {}", e.getResponseBodyAsString());
			return messerUtil.prepareSuccessResponse(DocumnetConstants.SUCCESS_CD,
					DocumnetConstants.SUCCESS_MSG, e.getResponseBodyAsString());

		} catch (Exception e) {
			logger.error("External Service Internal error# {}", e.getMessage());
			return messerUtil.prepareErrorResponse(DocumnetConstants.EXT_ERROR_CD,
					DocumnetConstants.EXT_ERROR_MSG);

		}
		return messerUtil.prepareErrorResponse(DocumnetConstants.EXT_ERROR_CD,
				DocumnetConstants.EXT_ERROR_MSG);
	}

	@Override
	public String invokePost(String serviceName, HttpHeaders headers, Object requestObj) {
		ObjectMapper objMapper = null;
		try {
			objMapper = new ObjectMapper();
			String requestPayload = objMapper.writeValueAsString(requestObj);
			logger.info("External Service url#{} Request#{}", serviceName, requestPayload);
			ResponseEntity<String> response = restTemplate.exchange(serviceName, HttpMethod.POST,
					new HttpEntity<>(requestObj, headers), String.class);

			logger.info("Event API post() service successfully done");
			logger.info("External Service Response# {}", response.getBody());

			return response.getBody();

		}catch (HttpStatusCodeException e) {
			logger.error("External Service Bad Response# {}", e.getResponseBodyAsString());
			return  e.getResponseBodyAsString();

		} catch (Exception e) {
			logger.error("External Service Internal error# {}", e.getMessage());
			/*return messerUtil.prepareErrorResponse(MesserApAutomationConstants.EXT_ERROR_CD,
					MesserApAutomationConstants.EXT_ERROR_MSG);*/

		}
		return null;
	}
}
