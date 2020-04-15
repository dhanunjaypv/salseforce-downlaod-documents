/**
 * 
 */
package com.poc.document.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.document.api.model.EventResponse;
import com.poc.document.api.model.LoginRequestDto;
import com.poc.document.api.model.UserInfo;
import com.poc.document.config.PropertiesConfig;
import com.poc.document.service.DocumentClient;
import com.poc.document.service.DownlaodDocumentService;
import com.poc.document.util.DocumentUtil;

/**
 * @author dhanunjaya.potteti
 *
 */
@Service
public class DownlaodDocumentServiceImpl implements DownlaodDocumentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DownlaodDocumentService.class);

	@Autowired
	PropertiesConfig properties;

	@Autowired
	DocumentUtil documentUtil;

	@Autowired
	DocumentClient client;
	@Autowired
	ModelMapper mapper;

	@Override
	public EventResponse downlaodDocument(LoginRequestDto loginRequestDto) {

		// 1. Get an active salesforce session ID/token enterprise API - login()
		// SOAP method

		EventResponse event = client.post(properties.getLoginUrl(), null, loginRequestDto);

		// 2. Get your organization ID ("org ID")Setup > Company Profile >
		// Company Information OR use the enterprise API getUserInfo() SOAP call
		// to retrieve your org ID
		UserInfo user = mapper.map(event.getData(), UserInfo.class);

		// 3. Send an HTTP GET request to https://{your sf.com
		// instance}.salesforce.com/ui/setup/export/DataExportPage/d?setupid=DataManagementExport
		event = client.get(properties.getFilesUrl(),
				documentUtil.prepareHttpHeaders(user.getOrgId(), user.getSessionId()));

		// 4. Parse the resulting HTML for instances of <a
		// href="/servlet/servlet.OrgExport?fileName=(The filename begins after
		// fileName=)

		List<String> fileNames = mapper.map(event.getData(), new TypeToken<List<String>>() {
		}.getType());

		// 5 Plug the file names into this URL to download (and
		// save):https://{your sf.com
		// instance}.salesforce.com/servlet/servlet.OrgExport?fileName={filename}Use
		// the same cookie as in step 3 when downloading the files
		for (String fileName : fileNames) {
			event = client.get(properties.getFileDownloadUrl() + fileName,
					documentUtil.prepareHttpHeaders(user.getOrgId(), user.getSessionId()));
		}

		return null;
	}

}
