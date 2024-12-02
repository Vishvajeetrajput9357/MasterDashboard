package com.Master_Dashboard.Controller;

import java.sql.Timestamp;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import com.Master_Dashboard.entity.CoreTempTrxn;
import com.Master_Dashboard.ex.util.GenrateUniqueId;
import com.Master_Dashboard.repository.CoreTempRepository;

@Controller
@RequestMapping("/nachRedirect")
public class PaymentLinkController {

	private CoreTempRepository coreTempRepository;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

	public PaymentLinkController(CoreTempRepository coreTempRepository) {
		this.coreTempRepository = coreTempRepository;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/paymentLink", method = RequestMethod.GET)
	public ModelAndView paymentLink(/* @RequestBody String reqbody */) {
		
		String reqbody="";
		LOGGER.info(reqbody);
		ModelAndView model = new ModelAndView();

		try {
			Timestamp trxnDate1 = Timestamp.valueOf(com.Master_Dashboard.ex.util.DateAndTime.getCurrentTimeInIST());

			String[] keyValuePairs = reqbody.split("&");

			// Iterate through key-value pairs
			for (String pair : keyValuePairs) {
				String[] entry = pair.split("=", 2);
				String key = URLDecoder.decode(entry[0], "UTF-8");
				String value = entry.length > 1 ? URLDecoder.decode(entry[1], "UTF-8") : "";

				if (key.equals("MandateRespDoc")) {
					// Convert single quotes to double quotes for JSON compatibility
					value = value.replace("'", "\"");

					ObjectMapper objectMapper = new ObjectMapper();
					Map<String, Object> json = objectMapper.readValue(value, Map.class);

					LOGGER.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));

					String response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

					CoreTempTrxn coreTempTrxn = new CoreTempTrxn();
					coreTempTrxn.setAppTrxnInfo("NA");
					coreTempTrxn.setTrxnInfo(response);
					coreTempTrxn.setTransactionDate(trxnDate1);
					coreTempTrxn.setRetryCount("Na");
					coreTempTrxn.setTransactionId("NA");
					coreTempRepository.save(coreTempTrxn);

				} else {
					// Print non-JSON key-value pairs
					LOGGER.info(key + ": " + value);
				}
			}

			model.addObject("Success", "ResponseCapture");

		} catch (Exception e) {

			e.printStackTrace();

			model.addObject("Failed", "ResponseNotCapture");

			LOGGER.error(e.getMessage());

		}
		model.setViewName("user/merchantRedirectPage");
		return model;
	}
	
	@RequestMapping(value = "/createMandate", method = RequestMethod.GET)
	public ModelAndView createMandate() {
		
		LOGGER.info("Inside genrate Madate form...");
		
		ModelAndView model = new ModelAndView();
		try {
			String uniqueId=GenrateUniqueId.generateUniqueId();

			LOGGER.info("uniqueId : {}",uniqueId);
			
//		"50100000835738|2024-11-25|2025-11-25|100.00|"
//		String authType="Debit";
//		String authType="NET";
			String authType="AADHAAR";
			model.addObject("messageId", uniqueId);
			model.addObject("Debit", authType);
			model.setViewName("user/CreateNach");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}