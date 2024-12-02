package com.Master_Dashboard.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.Master_Dashboard.request.CreateMandateRequest;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.CreateNachService;

@Service
public class CreateNachServiceImpl implements CreateNachService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateNachServiceImpl.class);

	@Override
	public Map<String, Object> createNach(CreateMandateRequest createMandateRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		LOGGER.info("ENach data : {}",createMandateRequest.toString());
		
		map.put(ResponseMessage.STATUS, ResponseMessage.STATUS_SUCCESS);
		map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.MANDATE_URL_SUCCESS);
		map.put("url", "https://empirical-tootsie-kjsstpay-6c9fa64e.koyeb.app/dashboard/nachRedirect/createMandate");
		return map;
	}
}