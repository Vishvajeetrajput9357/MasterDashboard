package com.Master_Dashboard.service;

import java.util.Map;

import javax.validation.Valid;

import com.Master_Dashboard.request.BankAccountVerificationReq;

public interface MerchantEkycService {

	Map<String, Object> merchantAadhaarGenrateOtp(String aadhaar);

	Map<String, Object> validateOtp(String otp, String mermerchantTrxnRefId,long merchantId);

	Map<String, Object> panVerify(String pan, long merchantId);

	Map<String, Object> merchantBankAccountVerification(
			BankAccountVerificationReq merchantBankAccountVerification, long merchantId);

}
