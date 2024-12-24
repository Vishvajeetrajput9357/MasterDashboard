package com.Master_Dashboard.Response;

public class EnachTrxnReportResPayload {

	private String sNo;
	private Long eNachTransactionId;
	private String transactionStatus;
	private String transactionDate;
	private String merchantTransactionRefId;
	private String transactionAmount;
	private char isReconcile;
	private char isSettled;
	private String serviceName;
	private String trxnRefId;
	private String mandateId;
	private String customerName;
	private String eNachUMRN;
	private Double charges;
	private String remark;
	private String debitDate;
	private String customerBankAccountNumber;
	private String customerBankIfsc;
	private String customerMobileNumber;

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public String getMerchantTransactionRefId() {
		return merchantTransactionRefId;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getTrxnRefId() {
		return trxnRefId;
	}

	public String getMandateId() {
		return mandateId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String geteNachUMRN() {
		return eNachUMRN;
	}

	public Double getCharges() {
		return charges;
	}

	public String getRemark() {
		return remark;
	}

	public String getDebitDate() {
		return debitDate;
	}

	public String getCustomerBankAccountNumber() {
		return customerBankAccountNumber;
	}

	public String getCustomerBankIfsc() {
		return customerBankIfsc;
	}

	public Long geteNachTransactionId() {
		return eNachTransactionId;
	}

	public void seteNachTransactionId(Long eNachTransactionId) {
		this.eNachTransactionId = eNachTransactionId;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setMerchantTransactionRefId(String merchantTransactionRefId) {
		this.merchantTransactionRefId = merchantTransactionRefId;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setTrxnRefId(String trxnRefId) {
		this.trxnRefId = trxnRefId;
	}

	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void seteNachUMRN(String eNachUMRN) {
		this.eNachUMRN = eNachUMRN;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setDebitDate(String debitDate) {
		this.debitDate = debitDate;
	}

	public void setCustomerBankAccountNumber(String customerBankAccountNumber) {
		this.customerBankAccountNumber = customerBankAccountNumber;
	}

	public void setCustomerBankIfsc(String customerBankIfsc) {
		this.customerBankIfsc = customerBankIfsc;
	}

	public char getIsReconcile() {
		return isReconcile;
	}

	public char getIsSettled() {
		return isSettled;
	}

	public void setIsReconcile(char isReconcile) {
		this.isReconcile = isReconcile;
	}

	public void setIsSettled(char isSettled) {
		this.isSettled = isSettled;
	}

	
	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	
	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	
	
}
