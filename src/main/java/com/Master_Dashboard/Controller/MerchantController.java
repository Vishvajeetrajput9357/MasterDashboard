package com.Master_Dashboard.Controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Master_Dashboard.request.LoginRequest;
import com.Master_Dashboard.request.MerchantRagisterRequest;
import com.Master_Dashboard.service.MerchantsService;

@RestController
@RequestMapping("merchantController")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MerchantController {

//	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

	@Autowired
	private MerchantsService merchantsService;

	@GetMapping("/")
	public String loginMerchant() {
		return "<h1 style='color:green'>everything is perfect</h1>";
	}

	@PostMapping("/login")
	public Map<String, Object> loginMerchant( @Valid @RequestBody LoginRequest loginRequest) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			if (loginRequest == null) {
				map.put("loggedIn", false);
				map.put("code", "0x0202");
				map.put("status", "Failure");
				map.put("description", "Invalid user email or password");
			} else {
				map = merchantsService.loginMerchant(loginRequest);
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "Failure");
			map.put("loggedIn", false);
			map.put("code", "0x0202");
			map.put("description", "Invalid user email or password");
		}
		return map;

	}

	@PostMapping("/merchantregistration")
	public Map<String, Object> merchantregistration(
			@Valid @RequestBody MerchantRagisterRequest merchantRagisterRequest) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			if (merchantRagisterRequest == null) {
				map.put("status", "Failure");
				map.put("code", "0x0202");
				map.put("description", "Request is invalid");
			} else {
				map = merchantsService.merchantRegister(merchantRagisterRequest);
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "Failure");
			map.put("code", "0x0205");
			map.put("description", "Something went wrong");
		}
		return map;
	}
	
	

}
