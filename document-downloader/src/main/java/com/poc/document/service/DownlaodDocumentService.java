/**
 * 
 */
package com.poc.document.service;

import com.poc.document.api.model.DocumentRequestDto;
import com.poc.document.api.model.EventResponse;
import com.poc.document.api.model.LoginRequestDto;

/**
 * @author dhanunjaya.potteti
 *
 */
public interface DownlaodDocumentService {

	EventResponse downlaodDocument(LoginRequestDto loginRequestDto);

}
