package com.Master_Dashboard.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import com.Master_Dashboard.request.ValidateOtp;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.MerchantInfo;
import com.Master_Dashboard.repository.MerchantInfoRepository;
import com.Master_Dashboard.repository.VerificationRepository;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.MerchantEkycService;

@RestController
@RequestMapping("merchantEkyc")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MerchantEkycController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

	@Autowired
	private MerchantEkycService merchantEkycService;

	@Autowired
	private MerchantInfoRepository merchantInfoRepository;

	@Autowired
	private VerificationRepository verificationRepository;
	
	
	@PostMapping("/aadhar/{aadhaar}")
    public Map<String, Object> merchantAadhaarGenerateOtp(
            @RequestHeader("Client-Id") String clientId,
            @RequestHeader("Client-Secret") String clientSecret,
            @PathVariable("aadhaar") String aadhaar) {

        Map<String, Object> response = new HashMap<>();

        try {
        	Optional<MerchantInfo> merchantInfoOpt = validateMerchant(clientId, clientSecret);
        	if (!merchantInfoOpt.isPresent()) {
        		return setUnauthorised(response);
        	}
        	 if (checkMerchantKycAlredyExist(merchantInfoOpt.get().getMerchantId(),Encryption.encString("AADHAAR"))) {
 				return setErrorResponse(response, ResponseMessage.FAILED,"Addhaar is already verifide");
 			}
            if (!isValidAadhaar(aadhaar)) {
                return setErrorResponse(response, ResponseMessage.FAILED, "Invalid Aadhaar Number.");
            }

            response = merchantEkycService.merchantAadhaarGenrateOtp(aadhaar);

        } catch (Exception e) {
            LOGGER.error("Error occurred while generating Aadhaar OTP", e);
            return setUnauthorised(response);
        }

        return response;
    }
	
	@PostMapping("/validateOtp")
    public Map<String, Object> merchantAadhaarValidateOtp(
            @RequestHeader("Client-Id") String clientId,
            @RequestHeader("Client-Secret") String clientSecret,
            @Valid @RequestBody ValidateOtp validateOtp) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<MerchantInfo> merchantInfoOpt = validateMerchant(clientId, clientSecret);
            if (!merchantInfoOpt.isPresent()) {
                return setUnauthorised(response);
            }
            if (checkMerchantKycAlredyExist(merchantInfoOpt.get().getMerchantId(),Encryption.encString("AADHAAR"))) {
				return setErrorResponse(response, ResponseMessage.FAILED,"Addhaar is already verifide");
			}
            response = merchantEkycService.validateOtp(validateOtp.getOtp(),validateOtp.getMermerchantTrxnRefId(),merchantInfoOpt.get().getMerchantId());

        } catch (Exception e) {
            LOGGER.error("Error occurred while generating Aadhaar OTP", e);
            return setUnauthorised(response);
        }

        return response;
    }
	
	@PostMapping("/panVerify/{pan}")
    public Map<String, Object> merchantPanVerify(
            @RequestHeader("Client-Id") String clientId,
            @RequestHeader("Client-Secret") String clientSecret,
            @PathVariable("pan") String pan) {

        Map<String, Object> response = new HashMap<>();

        try {
        	Optional<MerchantInfo> merchantInfoOpt = validateMerchant(clientId, clientSecret);
        	if (!merchantInfoOpt.isPresent()) {
        		return setUnauthorised(response);
        	}
        	 if (checkMerchantKycAlredyExist(merchantInfoOpt.get().getMerchantId(),Encryption.encString("PAN"))) {
 				return setErrorResponse(response, ResponseMessage.FAILED,"pan is already verifide");
 			}
            if (!isValidPan(pan)) {
                return setErrorResponse(response, ResponseMessage.FAILED, "Invalid pan Number.");
            }

            response = merchantEkycService.panVerify(pan,merchantInfoOpt.get().getMerchantId());

        } catch (Exception e) {
            LOGGER.error("Error occurred while generating Aadhaar OTP", e);
            return setUnauthorised(response);
        }

        return response;
    }
	
	public Map<String, Object> setUnauthorised(Map<String, Object> map) {
		map.put(ResponseMessage.CODE, ResponseMessage.UNAUTHORISED);
		map.put(ResponseMessage.DESCRIPTION, ResponseMessage.UNAUTHORISED_DESCRIPTION);
		map.put(ResponseMessage.FIELD, ResponseMessage.FIELD_I);
		return map;
	}
	 
	public boolean isMerchantActive(MerchantInfo merchantInfo) {
	        return "Y".equalsIgnoreCase(merchantInfo.getIsMerchantActive());
	}
	
	private Optional<MerchantInfo> validateMerchant(String clientId, String clientSecret) {
        try {
            String encryptedClientId = Encryption.encString(clientId);
            String encryptedClientSecret = Encryption.encString(clientSecret);

            return merchantInfoRepository.findByClientIdAndClientSecret(encryptedClientId, encryptedClientSecret);
        } catch (Exception e) {
            LOGGER.error("Error occurred during merchant validation", e);
            return Optional.empty();
        }
    }
	
	private boolean checkMerchantKycAlredyExist(long merchantId,String tyep) {
        try {

            return verificationRepository.existsByMerchantIdAndVerificationType(merchantId,tyep);
        } catch (Exception e) {
            LOGGER.error("Error occurred during merchant validation", e);
            return false;
        }
    }
	
	private boolean isValidAadhaar(String aadhaar) {
        return aadhaar != null && aadhaar.matches("\\d{12}");
    }
	
	private Map<String, Object> setErrorResponse(Map<String, Object> response, String code, String description) {
        response.put(ResponseMessage.CODE, code);
        response.put(ResponseMessage.DESCRIPTION, description);
        response.put(ResponseMessage.STATUS, ResponseMessage.API_STATUS_FAILED);
        return response;
    }

	private boolean isValidPan(String pan) {
	    return pan != null && pan.matches("[A-Z]{5}\\d{4}[A-Z]");
	}
	
}
