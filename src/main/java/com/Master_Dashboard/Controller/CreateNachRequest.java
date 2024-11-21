package com.Master_Dashboard.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Master_Dashboard.service.CreateNachService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/NachRequest")
public class CreateNachRequest {
	
	@Autowired private CreateNachService createNachService;
	
	@PostMapping("/merchantRegister")
	public Map<String, Object> merchantRegister() {
		Map<String, Object> map = new HashMap<>();
		try {
			
			return createNachService.createNach();

		} catch (Exception e) {
			return map;
		}
	}
	
}
