package com.poc.document.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.document.api.model.EventResponse;
import com.poc.document.config.PropertiesConfig;

@Component
public class DocumentUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentUtil.class);

	@Autowired
	private ApplicationContext applicationContext;

	
	@Autowired
	private PropertiesConfig properties;
	

	


	@SuppressWarnings("unchecked")
	public <T> T copyBeanProperties(Object source, Class<T> requiredType) {
		Object target = applicationContext.getBean(requiredType);
		BeanUtils.copyProperties(source, target);
		if (requiredType != null && requiredType.isInstance(target)) {
			return (T) target;
		}
		return null;
	}

	public String convertPojoToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return "";
	}



	
	public <T> T convertTypeReference(String erpResponse, Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(erpResponse, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public EventResponse prepareErrorResponse(String code, String msg) {
		EventResponse event = new EventResponse();
		event.setStatus(false);
		event.setStatusCode(code);
		event.setMessage(msg);
		return event;
	}

	public EventResponse prepareErrorResponse(String code, String msg, Object object) {
		EventResponse event = new EventResponse();
		event.setStatus(false);
		event.setData(object);
		event.setStatusCode(code);
		event.setMessage(msg);
		return event;
	}

	public EventResponse prepareSuccessResponse(String code, String msg, Object object) {
		EventResponse event = new EventResponse();
		event.setStatus(true);
		event.setData(object);
		event.setStatusCode(code);
		event.setMessage(msg);
		return event;
	}

	public EventResponse prepareSuccessResponse(String respCode, String respMsg) {
		EventResponse event = new EventResponse();
		event.setStatus(true);
		event.setStatusCode(respCode);
		event.setMessage(respMsg);
		return event;
	}

	
	public HttpHeaders prepareHttpHeaders( String oid, String sid) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		headers.set("oid", oid);
		headers.set("sid", sid);
/*
		headers.set("Authorization", String.format("Basic %s", Base64.getEncoder().encodeToString(
				String.format("%s:%s", properties.getUserName(), properties.getLnUserPwd()).getBytes())));*/
		return headers;
	}

}
