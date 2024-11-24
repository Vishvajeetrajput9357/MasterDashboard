package com.Master_Dashboard.serviceImpl;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;

import com.Master_Dashboard.Controller.MerchantController;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.Merchants;
import com.Master_Dashboard.entity.VerificationEntity;
import com.Master_Dashboard.repository.MerchantsRepository;
import com.Master_Dashboard.repository.VerificationRepository;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.MerchantEkycService;
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
			ResponseEntity<Map> response = restTemplate.exchange(GENERATE_OTP_URL + "" + aadharNumber, HttpMethod.POST,
					entity, Map.class);
			Map<String, Object> responseBody = response.getBody();

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
			ResponseEntity<Map> response = restTemplate.exchange(VALIDATE_OTP_URL, HttpMethod.POST, entity, Map.class);

			Map<String, Object> responseBody = response.getBody();

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
					map.put(ResponseMessage.DESCRIPTION, "Aadhaar verification has been successfully completed.");
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
			ResponseEntity<Map> response = restTemplate.exchange(PAN_URL + "" + pan, HttpMethod.POST, entity,
					Map.class);
			Map<String, Object> responseBody = response.getBody();

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

}
