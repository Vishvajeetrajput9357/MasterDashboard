package com.Master_Dashboard.Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Master_Dashboard.entity.CoreTempTrxn;
import com.Master_Dashboard.repository.CoreTempRepository;

@Controller
@RequestMapping("/nachRedirect")
public class PaymentLinkController {

private CoreTempRepository coreTempRepository;

	public PaymentLinkController(CoreTempRepository coreTempRepository)
	{
		 this.coreTempRepository=coreTempRepository;
	}
	
//	@PostMapping(value = "/paymentLink")
	@RequestMapping(value = "/paymentLink", method = RequestMethod.GET)
	public  ModelAndView paymentLink(/* @RequestBody String reqbody */) {
		String reqbody="";
		System.out.println(reqbody);
		Map<String,Object> map = new HashMap<String,Object>();
		ModelAndView model = new ModelAndView();

		try {
		
		Timestamp trxnDate1 = Timestamp.valueOf(com.Master_Dashboard.ex.util.DateAndTime.getCurrentTimeInIST());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		CoreTempTrxn coreTempTrxn = new CoreTempTrxn();
		coreTempTrxn.setAppTrxnInfo("NA");
		coreTempTrxn.setTrxnInfo(reqbody+"");
		coreTempTrxn.setTransactionDate(trxnDate1);
		coreTempTrxn.setRetryCount("Na"); 
		coreTempTrxn.setTransactionId("NA"); 
		coreTempRepository.save(coreTempTrxn);
		
		model.addObject("Success","ResponseCapture" );
		
		}catch(Exception e) {
			
			e.printStackTrace();
			model.addObject("Failed","ResponseNotCapture" );

			System.out.println(e.getMessage());

			System.out.println(reqbody);
		}
		System.out.println("Test");
		model.setViewName("user/merchantRedirectPage");
		return model;
	}
}
