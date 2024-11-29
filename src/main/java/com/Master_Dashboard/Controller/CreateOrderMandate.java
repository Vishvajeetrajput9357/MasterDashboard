package com.Master_Dashboard.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import com.Master_Dashboard.request.CreateMandateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Master_Dashboard.service.CreateNachService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/NachRequest")
public class CreateOrderMandate {
	
	@Autowired private CreateNachService createNachService;
	
	@PostMapping("/createMandate")
	public Map<String, Object> createMandate(@RequestHeader("Client-Id") String clientId,
			@RequestHeader("Client-Secret") String clientSecret, @Valid @RequestBody CreateMandateRequest CreateMandateRequest) {
		Map<String, Object> map = new HashMap<>();
		try {
			
			return createNachService.createNach();

		} catch (Exception e) {
			return map;
		}
	}
	
	
	

	
}
