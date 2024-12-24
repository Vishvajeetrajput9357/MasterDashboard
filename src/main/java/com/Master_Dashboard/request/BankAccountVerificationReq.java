package com.Master_Dashboard.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BankAccountVerificationReq {

	@NotBlank(message = "Bank account number cannot be blank.")
	@Size(min = 8, max = 18, message = "Bank account number must be between 8 and 20 digits.")
	private String bankAccountNumber;

	@NotBlank(message = "IFSC code cannot be blank.")
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code. It should match the Indian standard format (e.g., ABCD0123456).")
	private String ifsc;

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

}
