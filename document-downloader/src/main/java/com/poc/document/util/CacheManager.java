package com.poc.document.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "mcapCache")
public class CacheManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheManager.class);

	
	@Autowired
	CacheManager cacheManager;

	@CacheEvict(value = "documentCache", allEntries = true)
	public void clearCache() {
		// This method will remove all 'errorMessages' from cache
	}

	@PreDestroy
	public void cleanUp() {
		LOGGER.info(": Document Cache Manager cleanUp :");
		clearCache();
	}

	@PostConstruct
	public void initSVMPCacheManager() {
		LOGGER.info(": Documnet init CacheManager :");
		//putAllErrorCodes();
	}

	/*public void putAllErrorCodes() {
		List<ErrorMaster> errorMasters = (List<ErrorMaster>) errorMasterRepository.findAll();
		errorMasters.forEach(
				errorMaster -> cacheManager.getCache("mcapCache").put(errorMaster.getErrorCode(), errorMaster));
	}

	public ErrorMaster findByErrorCode(String errorCode) {
		return errorMasterRepository.findByErrorCode(errorCode);
	}
*/
}
