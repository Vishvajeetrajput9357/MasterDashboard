package com.Master_Dashboard.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreatePrasentationRequest {

	private String mandateId;
	
	private String accountHolderName;
	
	@Email(regexp = "^(?=.{1,64}@)[a-zA-Z0-9-_]+(\\.[a-zA-Z0-9-_]+)*@[^-][a-zA-Z0-9]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$")
	@NotBlank(message = "customerEmailId Email cannot be empty")
	@Size(min = 1, max = 47, message = "customerEmailId must be between 1 to 47 words")
	private String customerEmail;
	
	private String mobileNumber;

	private String bankIFSC;
	
	private String accountNumber;
	
	private String accountType;
	
	@NotBlank(message = "mandateDebitAmount cannot be blank")
	@Size(min = 1, max = 20, message = "mandateDebitAmount size must be between 1 and 20 characters")
	@Pattern(regexp = "^(?!0+$).*", message = "mandateDebitAmount cannot be zero")
	private String amount;
	
	@NotBlank(message = "settlementDate cannot be blank")
	@Size(min = 10, max = 10, message = "settlementDate must be exactly 10 characters (yyyy-MM-dd format)")
	@Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Please Pass yyyy-MM-dd format date on settlementDate parameter")
	private String settlementDate;

	public CreatePrasentationRequest(String mandateId, String accountHolderName, String customerEmail,
			String mobileNumber, String bankIFSC, String accountNumber, String accountType, String amount,
			String settlementDate) {
		this.mandateId = mandateId;
		this.accountHolderName = accountHolderName;
		this.customerEmail = customerEmail;
		this.mobileNumber = mobileNumber;
		this.bankIFSC = bankIFSC;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.amount = amount;
		this.settlementDate = settlementDate;
	}

	@Override
	public String toString() {
		return "CreatePrasentationRequest [mandateId=" + mandateId + ", accountHolderName=" + accountHolderName
				+ ", customerEmail=" + customerEmail + ", mobileNumber=" + mobileNumber + ", bankIFSC=" + bankIFSC
				+ ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", amount=" + amount
				+ ", settlementDate=" + settlementDate + "]";
	}

	public String getMandateId() {
		return mandateId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getBankIFSC() {
		return bankIFSC;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getAmount() {
		return amount;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setBankIFSC(String bankIFSC) {
		this.bankIFSC = bankIFSC;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

}
