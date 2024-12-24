package com.Master_Dashboard.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateMandateRequest {

	@NotBlank(message = "customerName cannot be blank")
	@Size(min = 1, max = 100, message = "customerName size must be between 1 and 100 characters")
	private String customerName;

	@Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$", message = "Please enter your 10 digit mobile number")
	@NotBlank(message = "customerMobile cannot be blank")
	@Size(min = 10, max = 15, message = "customerMobile size must be between 10 and 15 digits")
	private String customerMobile;

	@Email(regexp = "^(?=.{1,64}@)[a-zA-Z0-9-_]+(\\.[a-zA-Z0-9-_]+)*@[^-][a-zA-Z0-9]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$")
	@NotBlank(message = "customerEmailId Email cannot be empty")
	@Size(min = 1, max = 47, message = "customerEmailId must be between 1 to 47 words")
	private String customerEmailId;

	@NotBlank(message = "customerAccountNo cannot be blank")
	@Size(min = 8, max = 20, message = "customerAccountNo size must be between 8 and 20 digits")
	private String customerAccountNo;

	@NotBlank(message = "mandateStartDate cannot be blank")
	@Size(min = 10, max = 10, message = "mandateStartDate must be exactly 10 characters (yyyy-MM-dd format)")
	@Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Please Pass yyyy-MM-dd format date on mandateStartDate parameter")
	private String mandateStartDate;

	@NotBlank(message = "mandateExpiryDate cannot be blank")
	@Size(min = 10, max = 10, message = "mandateExpiryDate must be exactly 10 characters (yyyy-MM-dd format)")
	@Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Please Pass yyyy-MM-dd format date on mandateExpiryDate parameter")
	private String mandateExpiryDate;

	@NotBlank(message = "mandateDebitAmount cannot be blank")
	@Size(min = 1, max = 20, message = "mandateDebitAmount size must be between 1 and 20 characters")
	@Pattern(regexp = "^(?!0+$).*", message = "mandateDebitAmount cannot be zero")
	private String mandateDebitAmount;

	@NotBlank(message = "mandateCollectionAmount cannot be blank")
	@Size(min = 1, max = 20, message = "mandateCollectionAmount size must be between 1 and 20 characters")
	@Pattern(regexp = "^(?!0+$).*", message = "mandateCollectionAmount cannot be zero")
	private String mandateCollectionAmount;

//	@NotBlank(message = "mandateDebitFrequency cannot be blank")
//	@Size(min = 1, max = 10, message = "mandateDebitFrequency size must be between 1 and 10 characters")
//	@Pattern(regexp = "	", message = "mandateDebitFrequency")
	private String mandateDebitFrequency;

	@NotBlank(message = "debitType cannot be blank")
	@Pattern(regexp = "RCUR|OOFF", message = "Invalid debitType")
	@Size(min = 1, max = 20, message = "debitType size must be between 1 and 20 characters")
	private String debitType;

	@NotBlank(message = "customerbankIFSC cannot be blank")
	@Size(min = 11, max = 11, message = "customerbankIFSC must be exactly 11 characters")
	private String customerbankIFSC;

	@NotBlank(message = "authType cannot be blank")
	@Pattern(regexp = "Debit|NET|Aadhaar", message = "Invalid authType")
	@Size(min = 1, max = 20, message = "authType size must be between 1 and 20 characters")
	private String authType;

	@NotBlank(message = "bankAccountType cannot be blank")
	@Size(min = 1, max = 20, message = "bankAccountType size must be between 1 and 20 characters")
	@Pattern(regexp = "S|C|O", message = "Invalid bankAccountType")
	private String bankAccountType;

	@NotBlank(message = "bankName cannot be blank")
	@Size(min = 1, max = 50, message = "bankName size must be between 1 and 50 characters")
	private String bankName;

	@NotBlank(message = "additionalParam1 cannot be blank")
	@Size(min = 1, max = 50, message = "additionalParam1 size must be between 1 and 50 characters")
	private String additionalParam1;

	@NotBlank(message = "additionalParam2 cannot be blank")
	@Size(min = 1, max = 50, message = "additionalParam2 size must be between 1 and 50 characters")
	private String additionalParam2;

	@NotBlank(message = "additionalParam3 cannot be blank")
	@Size(min = 1, max = 50, message = "additionalParam3 size must be between 1 and 50 characters")
	private String additionalParam3;

	@NotBlank(message = "additionalParam4 cannot be blank")
	@Size(min = 1, max = 50, message = "additionalParam4 size must be between 1 and 50 characters")
	private String additionalParam4;

	@NotBlank(message = "additionalParam5 cannot be blank")
	@Size(min = 1, max = 50, message = "additionalParam5 size must be between 1 and 50 characters")
	private String additionalParam5;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getCustomerAccountNo() {
		return customerAccountNo;
	}

	public void setCustomerAccountNo(String customerAccountNo) {
		this.customerAccountNo = customerAccountNo;
	}

	public String getMandateStartDate() {
		return mandateStartDate;
	}

	public void setMandateStartDate(String mandateStartDate) {
		this.mandateStartDate = mandateStartDate;
	}

	public String getMandateExpiryDate() {
		return mandateExpiryDate;
	}

	public void setMandateExpiryDate(String mandateExpiryDate) {
		this.mandateExpiryDate = mandateExpiryDate;
	}

	public String getMandateDebitAmount() {
		return mandateDebitAmount;
	}

	public void setMandateDebitAmount(String mandateDebitAmount) {
		this.mandateDebitAmount = mandateDebitAmount;
	}

	public String getMandateCollectionAmount() {
		return mandateCollectionAmount;
	}

	public void setMandateCollectionAmount(String mandateCollectionAmount) {
		this.mandateCollectionAmount = mandateCollectionAmount;
	}

	public String getMandateDebitFrequency() {
		return mandateDebitFrequency;
	}

	public void setMandateDebitFrequency(String mandateDebitFrequency) {
		this.mandateDebitFrequency = mandateDebitFrequency;
	}

	public String getDebitType() {
		return debitType;
	}

	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}

	public String getCustomerbankIFSC() {
		return customerbankIFSC;
	}

	public void setCustomerbankIFSC(String customerbankIFSC) {
		this.customerbankIFSC = customerbankIFSC;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAdditionalParam1() {
		return additionalParam1;
	}

	public void setAdditionalParam1(String additionalParam1) {
		this.additionalParam1 = additionalParam1;
	}

	public String getAdditionalParam2() {
		return additionalParam2;
	}

	public void setAdditionalParam2(String additionalParam2) {
		this.additionalParam2 = additionalParam2;
	}

	public String getAdditionalParam3() {
		return additionalParam3;
	}

	public void setAdditionalParam3(String additionalParam3) {
		this.additionalParam3 = additionalParam3;
	}

	public String getAdditionalParam4() {
		return additionalParam4;
	}

	public void setAdditionalParam4(String additionalParam4) {
		this.additionalParam4 = additionalParam4;
	}

	public String getAdditionalParam5() {
		return additionalParam5;
	}

	public void setAdditionalParam5(String additionalParam5) {
		this.additionalParam5 = additionalParam5;
	}

	@Override
	public String toString() {
		return "CreateMandateRequest [customerName=" + customerName + ", customerMobile=" + customerMobile
				+ ", customerEmailId=" + customerEmailId + ", customerAccountNo=" + customerAccountNo
				+ ", mandateStartDate=" + mandateStartDate + ", mandateExpiryDate=" + mandateExpiryDate
				+ ", mandateDebitAmount=" + mandateDebitAmount + ", mandateCollectionAmount=" + mandateCollectionAmount
				+ ", mandateDebitFrequency=" + mandateDebitFrequency + ", debitType=" + debitType
				+ ", customerbankIFSC=" + customerbankIFSC + ", authType=" + authType + ", bankAccountType="
				+ bankAccountType + ", bankName=" + bankName + ", additionalParam1=" + additionalParam1
				+ ", additionalParam2=" + additionalParam2 + ", additionalParam3=" + additionalParam3
				+ ", additionalParam4=" + additionalParam4 + ", additionalParam5=" + additionalParam5 + "]";
	}

}