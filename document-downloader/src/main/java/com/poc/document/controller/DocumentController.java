/**
 * 
 */
package com.poc.document.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.document.api.model.DocumentRequestDto;
import com.poc.document.api.model.EventResponse;
import com.poc.document.api.model.LoginRequestDto;
import com.poc.document.config.PropertiesConfig;
import com.poc.document.service.DownlaodDocumentService;
import com.poc.document.util.DocumentUtil;
import com.poc.document.util.DocumnetConstants;

/**
 * @author dhanunjaya.potteti
 *
 */
@RestController
public class DocumentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	PropertiesConfig properties;
	
	@Autowired
	DocumentUtil documentUtil;
	
	@Autowired
	DownlaodDocumentService documentService;
	
	@PostMapping(value = "/signin")
	public ResponseEntity<String> signin(@RequestBody LoginRequestDto loginRequestDto) {

		LOGGER.info("Entered into signin {} ", loginRequestDto.getUsername());

	
		return ResponseEntity.ok("login");
	}

	
	@PostMapping(value = "/downlaod")
	public EventResponse downlaodDocument(@RequestBody LoginRequestDto loginRequestDto) {

		LOGGER.info("downloading file, User Name# {}  ", loginRequestDto.getUsername());

		EventResponse eventResponse=documentService.downlaodDocument(loginRequestDto);
		
		//return eventResponse;
		return documentUtil.prepareSuccessResponse(DocumnetConstants.SUCCESS_CD, DocumnetConstants.DOWNLOAD_DOCUMENT_SUCCESS_MSG);
	}
}
