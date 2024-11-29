package com.Master_Dashboard.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.CreateNachService;



@Service
public class CreateNachServiceImpl implements CreateNachService {

	@Override
	public Map<String, Object> createNach() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(ResponseMessage.STATUS, ResponseMessage.STATUS_SUCCESS);
		map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.MANDATE_URL_SUCCESS);
		map.put("url","https://empirical-tootsie-kjsstpay-6c9fa64e.koyeb.app/dashboard/nachRedirect/createMandate");
		 return map;
	}

	
}
