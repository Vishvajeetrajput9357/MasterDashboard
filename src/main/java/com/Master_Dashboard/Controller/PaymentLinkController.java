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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.CoreTempTrxn;
import com.Master_Dashboard.entity.ENachResponse;
import com.Master_Dashboard.entity.ENachTransactionDetails;
import com.Master_Dashboard.repository.CoreTempRepository;
import com.Master_Dashboard.repository.ENachResponseRepository;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;

@Controller
@RequestMapping("/nachRedirect")
public class PaymentLinkController {

	private CoreTempRepository coreTempRepository;
	private ENachResponseRepository eNachResponseRepository;
	private EnachTransactionDetailsRepository eNachTransactionDetailsRepository;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PaymentLinkController.class);

	private final static String UtilCode = "\\xf1f00613b15782a7acd5e9530c42cc753850a659882b3dc9668c48ddb4e4c531";
	private final static String Short_Code = "\\x255f1883ff812a8603d39695f1cd9592";

	public PaymentLinkController(CoreTempRepository coreTempRepository,
			EnachTransactionDetailsRepository eNachTransactionDetailsRepository, ENachResponseRepository eNachResponseRepository) {
		this.coreTempRepository = coreTempRepository;
		this.eNachTransactionDetailsRepository = eNachTransactionDetailsRepository;
		this.eNachResponseRepository = eNachResponseRepository;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/paymentLink", method = RequestMethod.POST)
	public synchronized ModelAndView paymentLink( @RequestBody String reqbody ) {
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
					
					try {
						
						com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
				        JsonNode rootNode = mapper.readTree(response);
						
						String merchantTrxnRefId=rootNode.get("MsgId").asText();
						
						Optional<ENachTransactionDetails> eNachTransactionDetails=
								eNachTransactionDetailsRepository.findByMerchantTransactionRefId(Encryption.encString(merchantTrxnRefId));
						
						if (!eNachTransactionDetails.isPresent()) {
							model.addObject("message", "Failed To Fetch Data..");
							return model;
						}
						String umrn="";	
						String mandateStatus="";
						String mandateMessage="";
						String transactionUpdateDate="";
						String trxnRefId="";
						long statusId=0;
						
				        String errorMessage =  rootNode.path("Errors").get(0).path("Error_Message").asText();

						ENachResponse eNachResponse = new ENachResponse();
						eNachResponse.setResponseDate(trxnDate1);
						eNachResponse.setApiResponse(response);
						eNachResponse.setMerchantTransactionRefId(merchantTrxnRefId);
						eNachResponse.setMerchantId(eNachTransactionDetails.get().getMerchantId());
					
						eNachResponse.setTrxnDate(trxnDate1+"");
						if (rootNode.get("Status").asText().equalsIgnoreCase("Failed")) {
							statusId=2;
							umrn="NA";
							mandateStatus="FAILED";
							mandateMessage=errorMessage+".";
							eNachResponse.setUmrn("NA");
							eNachResponse.setMandateId(rootNode.get("RefId").asText());
							trxnRefId=rootNode.get("RefId").asText();
							transactionUpdateDate=trxnDate1+"";
						}else if(rootNode.get("Status").asText().equalsIgnoreCase("Success")) {
							statusId=1;
							umrn=rootNode.get("Filler10").asText();
							trxnRefId=rootNode.get("Filler9").asText();
							mandateStatus="SUCCESS";
							mandateMessage="Mandate has been successfully registered.";
							transactionUpdateDate=trxnDate1+"";
							eNachResponse.setUmrn(umrn);
							eNachResponse.setMandateId(trxnRefId);
						}else {
							statusId=3;
							umrn="NA";
							trxnRefId="NA";
							mandateStatus="PENDING";
							mandateMessage=""+errorMessage;
							transactionUpdateDate=trxnDate1+"";
							eNachResponse.setUmrn("NA");
							eNachResponse.setMandateId("NA");
						}
						eNachResponseRepository.save(eNachResponse);
						
						ENachTransactionDetails eNachTransactionDetail =eNachTransactionDetails.get();
						eNachTransactionDetail.seteNachUMRN(Encryption.encString(umrn));
						eNachTransactionDetail.setRemark(Encryption.encString(mandateMessage));
						eNachTransactionDetail.setTransactionUpdateDate(transactionUpdateDate);
						eNachTransactionDetail.setResponseId(eNachResponse.getResponsetId());
						eNachTransactionDetail.setTrxnRefId(Encryption.encString(trxnRefId));
						eNachTransactionDetail.setTransactionStatus(mandateStatus);
						eNachTransactionDetail.setApiStatus(mandateStatus);
						eNachTransactionDetail.setTransactionStatusId(statusId);
						eNachTransactionDetailsRepository.save(eNachTransactionDetail);
						
					}catch(org.springframework.boot.json.JsonParseException ex) {
					}
					

				} else {
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

		ModelAndView model = new ModelAndView();
		try {
			Optional<ENachTransactionDetails> eNachTransactionDetails = eNachTransactionDetailsRepository
					.findByMerchantTransactionRefId(Encryption.encString(merchantTransactionRefId));

			if (!eNachTransactionDetails.isPresent()) {
				model.addObject("message", "Invalid token!!");
				model.setViewName("user/merchantRedirectPage");
				return model;
			}
			ENachTransactionDetails eNachTransactionDetail = eNachTransactionDetails.get();

			LOGGER.info("amount : {}", eNachTransactionDetail.getTransactionAmount());

			double transactionAmount = eNachTransactionDetail.getTransactionAmount();
			String formattedAmount = String.format("%.2f", transactionAmount);
			LOGGER.info("amount : {}", formattedAmount);

			String hashString = Encryption.decString(eNachTransactionDetail.getCustomerBankAccountNumber()) + "|"
					+ eNachTransactionDetail.getMandateStartDate() + "|" + eNachTransactionDetail.getMandateEndDate()
					+ "|" + formattedAmount + "|";

			LOGGER.info("hashString : {}", hashString);

			Thread.sleep(1000);
			
			String hash = AESEncrytDecry.checkSum(hashString);

			LOGGER.info(hash);
			LOGGER.info(" hey "+AESEncrytDecry.Encrypt(Encryption.decString(eNachTransactionDetail.getCustomerName())));
			LOGGER.info("kk "+AESEncrytDecry.Encrypt(Encryption.decString(eNachTransactionDetail.getCustomerMobileNumber())));
			LOGGER.info(" jj "+AESEncrytDecry.Encrypt(Encryption.decString(eNachTransactionDetail.getCustomerBankAccountNumber())));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getFrequency()));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getCategoryCode()));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getCustomerBankIfsc()));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getMandateType()));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getCustomerAccountType()));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getCustomerBankName()));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getMandateStartDate()));
			LOGGER.info(Encryption.decString(eNachTransactionDetail.getMandateStartDate()));
			
			model.addObject("merchantCategoryCode", "U099");
			model.addObject("utillyCode", UtilCode);
			model.addObject("shortCode", Short_Code);
			model.addObject("checkSum", hash);
			model.addObject("messageId", merchantTransactionRefId);
			model.addObject("customerName",
					AESEncrytDecry.Encrypt(Encryption.decString(eNachTransactionDetail.getCustomerName())));
			model.addObject("customerMobile",
					AESEncrytDecry.Encrypt(Encryption.decString(eNachTransactionDetail.getCustomerMobileNumber())));
			model.addObject("customerAccountNo", AESEncrytDecry
					.Encrypt(Encryption.decString(eNachTransactionDetail.getCustomerBankAccountNumber())));
			model.addObject("customerStartDate", eNachTransactionDetail.getMandateStartDate());
			model.addObject("customerExpiryDate", eNachTransactionDetail.getMandateEndDate());
			model.addObject("customerDebitAmount", formattedAmount);
			model.addObject("customerMaxAmount", "");
			model.addObject("customerDebitFrequency", Encryption.decString(eNachTransactionDetail.getFrequency()));
			model.addObject("customerSequenceType", Encryption.decString(eNachTransactionDetail.getCategoryCode()));
			model.addObject("customerInstructedMemberId",
					Encryption.decString(eNachTransactionDetail.getCustomerBankIfsc()));
			model.addObject("channel", Encryption.decString(eNachTransactionDetail.getMandateType()));
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