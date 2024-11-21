package com.Master_Dashboard.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.Master_Dashboard.service.CreateNachService;



@Service
public class CreateNachServiceImpl implements CreateNachService {

	@Override
	public Map<String, Object> createNach() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		 map.put("Sucess","http://localhost:8090/admin/redirect/paymentLink");
		 return map;
		
		
		
	}

	
}
