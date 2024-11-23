package com.Master_Dashboard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/redirect")
public class NachRedirectController {

	
	@RequestMapping(value = "/paymentLink", method = RequestMethod.GET)
	public ModelAndView paymentLink() {
		
		System.out.println("heyi");
		
		ModelAndView model = new ModelAndView();
		try {

			
//			"50100000835738|2024-11-25|2025-11-25|100.00|"
			
			model.setViewName("user/CreateNach");

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
		
	}
	
	
	
	
	
}
