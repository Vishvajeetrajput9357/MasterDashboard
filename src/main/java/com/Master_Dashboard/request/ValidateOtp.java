package com.Master_Dashboard.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ValidateOtp {

	@NotBlank
	@Size(max = 6,min=6,message = "Invalid otp")
	private String otp;
	
	@NotBlank
	@Size(max = 50,min=8,message = "Invalid mermerchantTrxnRefId")
	private String mermerchantTrxnRefId;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getMermerchantTrxnRefId() {
		return mermerchantTrxnRefId;
	}

	public void setMermerchantTrxnRefId(String mermerchantTrxnRefId) {
		this.mermerchantTrxnRefId = mermerchantTrxnRefId;
	}
	
	
}



