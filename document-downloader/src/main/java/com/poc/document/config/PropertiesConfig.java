/**
 * 
 */
package com.poc.document.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * @author dhanunjaya.potteti
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {
	@Value("${login.url}")
	private String loginUrl;

	
	@Value("${fileList.url}")
	private String filesUrl;
	@Value("${filedownload.url}")
	private String fileDownloadUrl;
	public String getLoginUrl() {
		return loginUrl;
	}
	

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}


	public String getFilesUrl() {
		return filesUrl;
	}


	public void setFilesUrl(String filesUrl) {
		this.filesUrl = filesUrl;
	}


	public String getFileDownloadUrl() {
		return fileDownloadUrl;
	}


	public void setFileDownloadUrl(String fileDownloadUrl) {
		this.fileDownloadUrl = fileDownloadUrl;
	}
	
	
	
	/*@Value("${ln.service}")
	private String lnService;

	@Value("${ln.service.podetails}")
	private String poDetailsUrl;

	@Value("${mobilor.user.name}")
	private String lnUserName;

	@Value("${mobilor.user.pwd}")
	private String lnUserPwd;

	@Value("${ln.service.company}")
	private String company;

	@Value("${ln.service.create.pinvoice}")
	private String createPInvocie;

	@Value("${ln.service.approve.invoice}")
	private String approveInvoice;

	@Value("${ln.service.create.cinvoice}")
	private String createCInvoice;

	@Value("${ln.service.update.cinvoice}")
	private String updateCInvocie;*/

}
