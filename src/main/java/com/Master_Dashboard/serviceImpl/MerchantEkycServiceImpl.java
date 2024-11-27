package com.Master_Dashboard.serviceImpl;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import com.Master_Dashboard.Controller.MerchantController;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.Merchants;
import com.Master_Dashboard.entity.VerificationEntity;
import com.Master_Dashboard.repository.MerchantsRepository;
import com.Master_Dashboard.repository.VerificationRepository;
import com.Master_Dashboard.request.BankAccountVerificationReq;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.MerchantEkycService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MerchantEkycServiceImpl implements MerchantEkycService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantEkycServiceImpl.class);

	private final MerchantsRepository merchantsRepository;

	private final VerificationRepository verificationRepository;

	private final RestTemplate restTemplate;
	private final String GENERATE_OTP_URL = "https://developer.fidypay.com/ekyc/aadhar/generateOtp/";
	private final String VALIDATE_OTP_URL = "https://developer.fidypay.com/ekyc/aadhar/validateOtp";
	private final String PAN_URL = "https://developer.fidypay.com/ekyc/pan/fetchPanV2/";
	private final String BANK_ACCOUNT_URL = "https://developer.fidypay.com/ekyc/accountVerification";

	public MerchantEkycServiceImpl(RestTemplate restTemplate, MerchantsRepository merchantsRepository,
			VerificationRepository verificationRepository) {
		this.restTemplate = restTemplate;
		this.merchantsRepository = merchantsRepository;
		this.verificationRepository = verificationRepository;
	}

	@Override
	public synchronized Map<String, Object> merchantAadhaarGenrateOtp(String aadharNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpHeaders headers = new HttpHeaders();
			getDefaultHeaders(headers);
			LOGGER.info("responseBody from headers : {}", headers);

			HttpEntity<String> entity = new HttpEntity<>(headers);
//			ResponseEntity<Map> response = restTemplate.exchange(GENERATE_OTP_URL + "" + aadharNumber, HttpMethod.POST,
//					entity, Map.class);
//			Map<String, Object> responseBody = response.getBody();
			
			String jsonResponse="{\r\n"
					+ "    \"code\": \"0x0200\",\r\n"
					+ "    \"description\": \"OTP generated and sent successfully on the registered mobile number. Kindly trigger the Validate OTP API within the next 10 minutes.\",\r\n"
					+ "    \"merchantTxnRefId\": \"5049C55C4E224E3AB8FD3D932F1BF207\",\r\n"
					+ "    \"status\": \"Success\"\r\n"
					+ "}";
			 ObjectMapper objectMapper = new ObjectMapper();

	            // Convert JSON string to Map
	            Map<String, Object> responseBody = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});


			if (responseBody != null) {
				String status = (String) responseBody.get("status");
				String code = (String) responseBody.get("code");

				if ("0x0200".equals(code) && "Success".equalsIgnoreCase(status)) {

					String description = (String) responseBody.get("description");
					String merchantTxnRefId = (String) responseBody.get("merchantTxnRefId");

					if ("OTP generated and sent successfully on the registered mobile number. Kindly trigger the Validate OTP API within the next 10 minutes."
							.equalsIgnoreCase(description)) {
						map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
						map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_SUCCESS);
						map.put(ResponseMessage.DESCRIPTION, "The OTP request has been successfully generated.");
						map.put(ResponseMessage.MERCHANT_TRXN_ID_, merchantTxnRefId);
					} else {
						map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
						map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_FAILED);
						map.put(ResponseMessage.DESCRIPTION, "UIDAI server is down.Please try after Some time.");
						map.put(ResponseMessage.MERCHANT_TRXN_ID_, merchantTxnRefId);
					}

				} else if ("0x0202".equals(code) || "0x0205".equals(code) || "Failed".equalsIgnoreCase(status)) {
					String description = (String) responseBody.get("description");

					LOGGER.info("responseBody from Fidypay : {}", responseBody);
					setErrorResponse(map, ResponseMessage.FAILED, description);

				} else {
					LOGGER.info("responseBody from Fidypay : {}", responseBody);

					setApiStatusFailed(map);
				}
			} else {
				LOGGER.info("responseBody from Fidypay : {}", responseBody);
				setApiStatusSomethingWent(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			setApiStatusSomethingWent(map);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized Map<String, Object> validateOtp(String otp, String merchantTxnRefId, long merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("otp", otp);
			requestBody.put("merchantTxnRefId", merchantTxnRefId);
			HttpHeaders headers = new HttpHeaders();
			getDefaultHeaders(headers);
			HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
//			ResponseEntity<Map> response = restTemplate.exchange(VALIDATE_OTP_URL, HttpMethod.POST, entity, Map.class);

//			Map<String, Object> responseBody = response.getBody();
//			Map<String, Object> responseBody = "";
			
			 String jsonResponse = " {\r\n"
			 		+ "	                    \"merchantReferenceNumber\": \"237020241126093910077\",\r\n"
			 		+ "	                    \"code\": \"0x0200\",\r\n"
			 		+ "	                    \"merchantProofOfIdentity\": {\r\n"
			 		+ "	                        \"dob\": \"05-07-2000\",\r\n"
			 		+ "	                        \"hashedEmail\": \"\",\r\n"
			 		+ "	                        \"gender\": \"M\",\r\n"
			 		+ "	                        \"name\": \"Vishvajeet\",\r\n"
			 		+ "	                        \"mobileNumber\": \"9826359752\"\r\n"
			 		+ "	                    },\r\n"
			 		+ "	                    \"description\": \"Aadhaar data retrieved successfully.\",\r\n"
			 		+ "	                    \"merchantProofOfAddress\": {\r\n"
			 		+ "	                        \"careOf\": \"S/O Dhiraj\",\r\n"
			 		+ "	                        \"country\": \"India\",\r\n"
			 		+ "	                        \"district\": \"Dewas\",\r\n"
			 		+ "	                        \"house\": \"Makan N0 311\",\r\n"
			 		+ "	                        \"landmark\": \"Mata Mandir Bke Pass\",\r\n"
			 		+ "	                        \"locality\": \"Devgarh\",\r\n"
			 		+ "	                        \"pincode\": \"455223\",\r\n"
			 		+ "	                        \"postOffice\": \"Deogarh\",\r\n"
			 		+ "	                        \"state\": \"Madhya Pradesh\",\r\n"
			 		+ "	                        \"street\": \"Ward N0 7\",\r\n"
			 		+ "	                        \"subDistrict\": \"\",\r\n"
			 		+ "	                        \"vtc\": \"Deogarh\"\r\n"
			 		+ "	                    },\r\n"
			 		+ "	                    \"status\": \"Success\"\r\n"
			 		+ "	                }";

	        // Create ObjectMapper instance
			 ObjectMapper objectMapper = new ObjectMapper();

	            // Convert JSON string to Map
	            Map<String, Object> responseBody = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});

			if (responseBody != null) {
				String status = (String) responseBody.get("status");
				String code = (String) responseBody.get("code");
				String description = (String) responseBody.get("description");

				if ("0x0200".equals(code) && "Success".equalsIgnoreCase(status)
						&& "Aadhaar data retrieved successfully.".equalsIgnoreCase(description)) {

					Map<String, Object> proofOfIdentity = (Map<String, Object>) responseBody
							.get("merchantProofOfIdentity");
					String gender = (String) proofOfIdentity.get("gender");
					Map<String, Object> proofOfAddress = (Map<String, Object>) responseBody
							.get("merchantProofOfAddress");

					String district = (String) proofOfAddress.get("district");
					String house = (String) proofOfAddress.get("house");
					String landmark = (String) proofOfAddress.get("landmark");
					String locality = (String) proofOfAddress.get("locality");
					String pincode = (String) proofOfAddress.get("pincode");
					String postOffice = (String) proofOfAddress.get("postOffice");
					String state = (String) proofOfAddress.get("state");
					String street = (String) proofOfAddress.get("street");
					String vtc = (String) proofOfAddress.get("vtc");

					Merchants merchants = merchantsRepository.findById(merchantId).get();
					merchants.setMerchantAddress1(
							Encryption.encString(house + " " + landmark + " " + locality + " " + district));
					merchants.setMerchantAddress2(Encryption.encString(postOffice + " " + street + " " + vtc));
					merchants.setMerchantCity(Encryption.encString(locality));
					merchants.setMerchantState(Encryption.encString(state));
					merchants.setMerchantCountry(Encryption.encString("India"));
					merchants.setMerchantZipcode(Encryption.encString(pincode));
					merchants.setGender(gender.charAt(0));
					merchants.setMerchantNationality(Encryption.encString("Indian"));
					merchantsRepository.save(merchants);

					try {
						VerificationEntity verificationEntity = new VerificationEntity();
						verificationEntity.setMerchantId(merchantId);
						verificationEntity.setVerificationRequestDetails(Encryption.encString("NA"));
						verificationEntity.setVerificationType(Encryption.encString("AADHAAR"));
						verificationEntity.setIsEkycVerifide("Y");
						verificationRepository.save(verificationEntity);
					} catch (Exception e) {
					}

					map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
					map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_SUCCESS);
					map.put(ResponseMessage.DESCRIPTION, description);
					map.put(ResponseMessage.MERCHANT_TRXN_ID_, merchantTxnRefId);

				} else {
					LOGGER.info("responseBody from Fidypay : {}", responseBody);
					setApiStatusFailed(map);
				}
			} else {
				LOGGER.info("responseBody from Fidypay : {}", responseBody);
				setApiStatusFailed(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setApiStatusSomethingWent(map);
		}

		return map;
	}

	@Override
	public Map<String, Object> panVerify(String pan,long merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpHeaders headers = new HttpHeaders();
			getDefaultHeaders(headers);
			LOGGER.info("responseBody from headers : {}", headers);

			HttpEntity<String> entity = new HttpEntity<>(headers);
//			ResponseEntity<Map> response = restTemplate.exchange(PAN_URL + "" + pan, HttpMethod.POST, entity,
//					Map.class);
//			Map<String, Object> responseBody = response.getBody();

			String jsonResponse="{\"typeOfHolder\":\"Individual or Person\",\"lastName\":\"CHOUHAN\",\"code\":\"0x0200\",\"panStatusCode\":\"E\",\"isValid\":true,\"description\":\"Data fetch successfully.\",\"isIndividual\":true,\"title\":\"\",\"panStatus\":\"VALID\",\"number\":\"CMWPC9247B\",\"firstName\":\"HEMENDRA\",\"aadhaarSeedingStatusCode\":\"Y\",\"name\":\"HEMENDRA SINGH CHOUHAN\",\"lastUpdatedOn\":\"\",\"middleName\":\"SINGH\",\"aadhaarSeedingStatus\":\"Successful\",\"status\":\"SUCCESS\"}\r\n"
					+ "";
			 ObjectMapper objectMapper2 = new ObjectMapper();

	            // Convert JSON string to Map
	            Map<String, Object> responseBody = objectMapper2.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});

			if (responseBody != null) {
				String status = (String) responseBody.get("status");
				String code = (String) responseBody.get("code");

				if ("0x0200".equals(code) && "Success".equalsIgnoreCase(status)) {

					String description = (String) responseBody.get("description");

					if ("Data fetch successfully.".equalsIgnoreCase(description)) {
						map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
						map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_SUCCESS);
						map.put(ResponseMessage.DESCRIPTION, "The Pan verification has been successful.");
						
						try {
				            ObjectMapper objectMapper = new ObjectMapper();
				            String jsonString = objectMapper.writeValueAsString(responseBody);

							VerificationEntity verificationEntity = new VerificationEntity();
							verificationEntity.setMerchantId(merchantId);
							verificationEntity.setVerificationRequestDetails(Encryption.encString(jsonString));
							verificationEntity.setVerificationType(Encryption.encString("PAN"));
							verificationEntity.setIsEkycVerifide("Y");
							verificationRepository.save(verificationEntity);
							
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
						map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_FAILED);
						map.put(ResponseMessage.DESCRIPTION, "Please try after Some time.");
					}

				} else if ("0x0202".equals(code) || "0x0205".equals(code) || "Failed".equalsIgnoreCase(status)) {
					String description = (String) responseBody.get("description");

					LOGGER.info("responseBody from Fidypay : {}", responseBody);
					setErrorResponse(map, ResponseMessage.FAILED, description);

				} else {
					LOGGER.info("responseBody from Fidypay : {}", responseBody);

					setApiStatusFailed(map);
				}
			} else {
				LOGGER.info("responseBody from Fidypay : {}", responseBody);
				setApiStatusSomethingWent(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			setApiStatusSomethingWent(map);
		}
		return map;
	}

	public void getDefaultHeaders(HttpHeaders headers) {

		headers.set("accept", "*/*");
		headers.set("Authorization", "Basic VmlzaHZhamVldDpWUkAxMjM=");
		headers.set("Client-Id", "sG2uD5wDMmiKLRp+K2MZyg==");
		headers.set("Client-Secret", "BgBdnFJvBRTRgYuc4QEv6fgL0/6ua9HiNXSYCSCOFt4=");
		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	public void setApiStatusFailed(Map<String, Object> map) {
		map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
		map.put(ResponseMessage.DESCRIPTION, "Invalid Request..");
		map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_FAILED);
	}

	public void setApiStatusSomethingWent(Map<String, Object> map) {
		map.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
		map.put(ResponseMessage.CODE, ResponseMessage.SOMETHING_WENT_WRONG);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.SOMETHING_WENT_WRONG_DESCRIPTION);
	}

	private Map<String, Object> setErrorResponse(Map<String, Object> response, String code, String description) {
		response.put(ResponseMessage.CODE, code);
		response.put(ResponseMessage.DESCRIPTION, description);
		response.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
		return response;
	}

	@Override
	public Map<String, Object> merchantBankAccountVerification(
			BankAccountVerificationReq merchantBankAccountVerification, long merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, String> requestBody = new HashMap<>();
			
			requestBody.put("beneficiaryAccNo", merchantBankAccountVerification.getBankAccountNumber());
			requestBody.put("beneficiaryIfscCode", merchantBankAccountVerification.getIfsc());
			requestBody.put("merchantTrxnRefId", generateUniqueKey());
			
			HttpHeaders headers = new HttpHeaders();
			getDefaultHeaders(headers);
			HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
			ResponseEntity<Map> response = restTemplate.exchange(BANK_ACCOUNT_URL, HttpMethod.POST, entity, Map.class);

			Map<String, Object> responseBody = response.getBody();

			if (responseBody != null) {
				
				LOGGER.info("responseBody from Fidypay : {}", responseBody);

				String status = (String) responseBody.get("status");
				String code = (String) responseBody.get("code");
				String description = (String) responseBody.get("description");

				if ("0x0200".equals(code) && "Success".equalsIgnoreCase(status)) {

					if ("Data fetch successfully.".equalsIgnoreCase(description) || "Account verified successfully".equalsIgnoreCase(description)) {
						map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
						map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_SUCCESS);
						map.put(ResponseMessage.DESCRIPTION, "The Bank Account verification has been successful.");
						
						try {
				            ObjectMapper objectMapper = new ObjectMapper();
				            String jsonString = objectMapper.writeValueAsString(responseBody);

							VerificationEntity verificationEntity = new VerificationEntity();
							verificationEntity.setMerchantId(merchantId);
							verificationEntity.setVerificationRequestDetails(Encryption.encString(jsonString));
							verificationEntity.setVerificationType(Encryption.encString("BANKACCOUNT"));
							verificationEntity.setIsEkycVerifide("Y");
							verificationRepository.save(verificationEntity);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						map.put(ResponseMessage.CODE, ResponseMessage.FAILED);
						map.put(ResponseMessage.FIELD, ResponseMessage.API_STATUS_FAILED);
						map.put(ResponseMessage.DESCRIPTION, description);
					}

				} else if ("0x0202".equals(code) || "0x0205".equals(code) || "Failed".equalsIgnoreCase(status)) {

					LOGGER.info("responseBody from Fidypay : {}", responseBody);
					setErrorResponse(map, ResponseMessage.FAILED, description);

				} else {
					LOGGER.info("responseBody from Fidypay : {}", responseBody);

					setApiStatusFailed(map);
				}
			} else {
				LOGGER.info("responseBody from Fidypay : {}", responseBody);
				setApiStatusSomethingWent(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			setApiStatusSomethingWent(map);
		}
		return map;
		
	}

	 public static String generateUniqueKey() {
	        // Get the current date and time
	        LocalDateTime now = LocalDateTime.now();
	        
	        // Format the date and time to a compact form
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"); // 15 digits (yy: 2, MM: 2, dd: 2, HH: 2, mm: 2, ss: 2, SSS: 3)
	        String formattedDateTime = now.format(formatter);
	        
	        // Generate a short random UUID
	        String randomPart = UUID.randomUUID().toString().substring(0, 5); // 5 characters
	        
	        // Combine and limit to 20 characters
	        return (formattedDateTime + randomPart).substring(0, 20);
	    }
	
	
	 
	 
	 
//	 Acc: 50100000835738
//	 IFSC: HDFC0003354
//	 Cust Id: 50187305
//	 PW: bank1234
//	 OTP: 123456
//
//	 50100000835738|2024-10-28|2025-10-28|100.00|
//

}
