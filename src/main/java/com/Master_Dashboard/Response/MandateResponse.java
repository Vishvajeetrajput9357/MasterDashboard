package com.Master_Dashboard.Response;

public class MandateResponse {

	private String maxAmount;
	private String frequency;
	private String startDate;
	private String endDate;
	private String mandateType;
	private String merchantTransactionRefId;
	private String mandateId;
	private String eNachUMRN;
	private String customerName;
	private String customerEmail;
	private String customerMobile;
	private String customerBankAccountNumber;
	private String customerBankIfsc;
	private String bankName;
	private String accountType;
	private String transactionStatus;

	public MandateResponse(String maxAmount, String frequency, String startDate, String endDate, String mandateType,
			String merchantTransactionRefId, String mandateId, String eNachUMRN, String customerName,
			String customerEmail, String customerMobile, String customerBankAccountNumber, String customerBankIfsc,
			String bankName, String accountType, String transactionStatus) {
		this.maxAmount = maxAmount;
		this.frequency = frequency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mandateType = mandateType;
		this.merchantTransactionRefId = merchantTransactionRefId;
		this.mandateId = mandateId;
		this.eNachUMRN = eNachUMRN;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerBankAccountNumber = customerBankAccountNumber;
		this.customerBankIfsc = customerBankIfsc;
		this.bankName = bankName;
		this.accountType = accountType;
		this.transactionStatus = transactionStatus;
		this.customerMobile=customerMobile;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public String getFrequency() {
		return frequency;
	}

	public String getMandateType() {
		return mandateType;
	}

	public String getMerchantTransactionRefId() {
		return merchantTransactionRefId;
	}

	public String getMandateId() {
		return mandateId;
	}

	public String geteNachUMRN() {
		return eNachUMRN;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public String getCustomerBankAccountNumber() {
		return customerBankAccountNumber;
	}

	public String getCustomerBankIfsc() {
		return customerBankIfsc;
	}

	public String getBankName() {
		return bankName;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	public void setMerchantTransactionRefId(String merchantTransactionRefId) {
		this.merchantTransactionRefId = merchantTransactionRefId;
	}

	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}

	public void seteNachUMRN(String eNachUMRN) {
		this.eNachUMRN = eNachUMRN;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public void setCustomerBankAccountNumber(String customerBankAccountNumber) {
		this.customerBankAccountNumber = customerBankAccountNumber;
	}

	public void setCustomerBankIfsc(String customerBankIfsc) {
		this.customerBankIfsc = customerBankIfsc;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
