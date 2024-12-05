package com.Master_Dashboard.ex.util;

import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.MerchantInfo;
import com.Master_Dashboard.repository.MerchantInfoRepository;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.serviceImpl.MerchantEkycServiceImpl;

public class SetErrorResponses {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantEkycServiceImpl.class);
	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;
	
	public Map<String, Object> setUnauthorised(Map<String, Object> map) {
		map.put(ResponseMessage.CODE, ResponseMessage.UNAUTHORISED);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.UNAUTHORISED_DESCRIPTION);
		map.put(ResponseMessage.FIELD, ResponseMessage.FIELD_I);
		return map;
	}
	
	public Map<String, Object> setApiStatusSomethingWent(Map<String, Object> map) {
		map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
		map.put(ResponseMessage.CODE, ResponseMessage.SOMETHING_WENT_WRONG);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.SOMETHING_WENT_WRONG_DESCRIPTION);
		return map;
	}
	
	public Optional<MerchantInfo> validateMerchant(String clientId, String clientSecret) {
		try {
			String encryptedClientId = Encryption.encString(clientId);
			String encryptedClientSecret = Encryption.encString(clientSecret);

			return merchantInfoRepository.findByClientIdAndClientSecret(encryptedClientId, encryptedClientSecret);
		} catch (Exception e) {
			LOGGER.error("Error occurred during merchant validation", e);
			return Optional.empty();
		}
	}
	
	public Map<String, Object> setErrorResponseWith(Map<String, Object> response, String code, String description) {
		response.put(ResponseMessage.CODE, code);
		response.put(ResponseMessage.DESCRIPTION, description);
		response.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
		return response;
	}
	
}