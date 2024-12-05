package com.Master_Dashboard.Controller;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.CoreTempTrxn;
import com.Master_Dashboard.entity.ENachTransactionDetails;
import com.Master_Dashboard.repository.CoreTempRepository;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;

@Controller
@RequestMapping("/nachRedirect")
public class PaymentLinkController {

	private CoreTempRepository coreTempRepository;
	private EnachTransactionDetailsRepository eNachTransactionDetailsRepository;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PaymentLinkController.class);

	private final static String UtilCode = "\\xf1f00613b15782a7acd5e9530c42cc753850a659882b3dc9668c48ddb4e4c531";
	private final static String Short_Code = "\\x255f1883ff812a8603d39695f1cd9592";

	
	public PaymentLinkController(CoreTempRepository coreTempRepository,
			EnachTransactionDetailsRepository eNachTransactionDetailsRepository) {
		this.coreTempRepository = coreTempRepository;
		this.eNachTransactionDetailsRepository = eNachTransactionDetailsRepository;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/paymentLink", method = RequestMethod.POST)
	public ModelAndView paymentLink(@RequestBody String reqbody) {

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

			model.addObject("message", "ResponseCapture");

		} catch (Exception e) {

			e.printStackTrace();

			model.addObject("message", "ResponseNotCapture");

			LOGGER.error(e.getMessage());

		}
		model.setViewName("user/merchantRedirectPage");
		return model;
	}

	@RequestMapping(value = "/createMandate/{merchantTransactionRefId}", method = RequestMethod.GET)
	public ModelAndView createMandate(@PathVariable String merchantTransactionRefId) {

		LOGGER.info("Inside genrate Madate form...");

		ModelAndView model = new ModelAndView();
		try {
			Optional<ENachTransactionDetails> eNachTransactionDetails = eNachTransactionDetailsRepository
					.findByMerchantTransactionRefId(Encryption.encString(null));

			if (!eNachTransactionDetails.isPresent()) {
				model.addObject("message", "Invalid token!!");
				model.setViewName("user/merchantRedirectPage");
				return model;
			}
			ENachTransactionDetails eNachTransactionDetail=eNachTransactionDetails.get();
			LOGGER.info("merchantTransactionRefId : {}", merchantTransactionRefId);

			String authType="NET";
			
	        String hash=Encryption.decString(eNachTransactionDetail.getCustomerBankAccountNumber())+
	        		"|"+eNachTransactionDetail.getMandateStartDate()+"|"+eNachTransactionDetail.getMandateEndDate()+"|"+
	        		eNachTransactionDetail.getTransactionAmount()+"|";

			model.addObject("merchantCategoryCode", "U009");
			model.addObject("utillyCode", UtilCode);
			model.addObject("shortCode", Short_Code);
			model.addObject("checkSum", AESEncrytDecry.checkSum(hash));
			model.addObject("messageId", merchantTransactionRefId);
			model.addObject("customerName", Encryption.decString(eNachTransactionDetail.getCustomerName()));
			model.addObject("customerMobile", Encryption.decString(eNachTransactionDetail.getCustomerMobileNumber()));
			model.addObject("customerAccountNo", Encryption.decString(eNachTransactionDetail.getCustomerBankAccountNumber()));
			model.addObject("customerStartDate", eNachTransactionDetail.getMandateStartDate());
			model.addObject("customerExpiryDate", eNachTransactionDetail.getMandateEndDate());
			model.addObject("customerDebitAmount", eNachTransactionDetail.getTransactionAmount());
			model.addObject("customerMaxAmount", "");
			model.addObject("customerDebitFrequency", Encryption.decString(eNachTransactionDetail.getFrequency()));
			model.addObject("customerSequenceType", Encryption.decString(eNachTransactionDetail.getMandateType()));
			model.addObject("customerInstructedMemberId", Encryption.decString(eNachTransactionDetail.getCustomerBankIfsc()));
			model.addObject("channel", authType);
			model.addObject("filler5", Encryption.decString(eNachTransactionDetail.getCustomerAccountType()));
			model.addObject("filler6", Encryption.decString(eNachTransactionDetail.getCustomerBankName()));
			model.setViewName("user/CreateNach");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("message", "Something went wrong!!");
			model.setViewName("user/merchantRedirectPage");
			return model;
		}
		return model;
	}
}