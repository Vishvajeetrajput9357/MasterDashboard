package com.Master_Dashboard.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ENACH_REQUEST")
@EntityListeners(AuditingEntityListener.class)
public class ENachRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQUEST_ID")
	private Long requestId;

	@Column(name = "REQUEST_DATE", nullable = false)
	private Timestamp requestDate;

	@Column(name = "CUSTOMER_MOBILE_NO", length = 100, nullable = false)
	private String customerMobileNumber;

	@Column(name = "TRXN_AMOUNT", nullable = false)
	private double transactionAmount;

	@Column(name = "MANDATE_START_DATE", length = 100, nullable = false)
	private String mandateStartDate;
	
	@Column(name = "MANDATE_END_DATE", length = 100, nullable = false)
	private String mandateEndDate;

	@Column(name = "CUSTOMER_NAME", length = 100, nullable = false)
	private String customerName;

	@Column(name = "CUSTOMER_BANK_ACCOUNT_NO", length = 100, nullable = false)
	private String customerBankAccountNumber;

	@Column(name = "CUSTOMER_BANK_IFSC", length = 100, nullable = false)
	private String customerBankIfsc;

	@Column(name = "CUSTOMER_BANK_NAME", length = 100, nullable = false)
	private String customerBankName;

	@Column(name = "CUSTOMER_ACCOUNT_TYPE", length = 100, nullable = false)
	private String customerAccountType;

	@Column(name = "CATEGORY_CODE", length = 100, nullable = false)
	private String categoryCode;

	@Column(name = "FREQUENCY", length = 100, nullable = false)
	private String frequency;

	@Column(name = "MERCHANT_ID", nullable = false)
	private long merchantId;

	@Column(name = "MERCHANT_TRANSACTION_REF_ID", length = 100, nullable = false)
	private String merchantTransactionRefId;
	
	@Column(name = "UMRN", length = 100, nullable = false)
	private String umrn="NA";

	@Column(name = "DEBIT_DATE", length = 100, nullable = false)
	private String debitDate="NA";


	@Column(name = "API_REQUEST", length = 1500, nullable = false)
	private String apiRequest;


	public Long getRequestId() {
		return requestId;
	}


	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}


	public Timestamp getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}


	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}


	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}


	public double getTransactionAmount() {
		return transactionAmount;
	}


	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	public String getMandateStartDate() {
		return mandateStartDate;
	}


	public void setMandateStartDate(String mandateStartDate) {
		this.mandateStartDate = mandateStartDate;
	}


	public String getMandateEndDate() {
		return mandateEndDate;
	}


	public void setMandateEndDate(String mandateEndDate) {
		this.mandateEndDate = mandateEndDate;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerBankAccountNumber() {
		return customerBankAccountNumber;
	}


	public void setCustomerBankAccountNumber(String customerBankAccountNumber) {
		this.customerBankAccountNumber = customerBankAccountNumber;
	}


	public String getCustomerBankIfsc() {
		return customerBankIfsc;
	}


	public void setCustomerBankIfsc(String customerBankIfsc) {
		this.customerBankIfsc = customerBankIfsc;
	}


	public String getCustomerBankName() {
		return customerBankName;
	}


	public void setCustomerBankName(String customerBankName) {
		this.customerBankName = customerBankName;
	}


	public String getCustomerAccountType() {
		return customerAccountType;
	}


	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}


	public String getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	public String getFrequency() {
		return frequency;
	}


	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


	public long getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}


	public String getMerchantTransactionRefId() {
		return merchantTransactionRefId;
	}


	public void setMerchantTransactionRefId(String merchantTransactionRefId) {
		this.merchantTransactionRefId = merchantTransactionRefId;
	}


	public String getUmrn() {
		return umrn;
	}


	public void setUmrn(String umrn) {
		this.umrn = umrn;
	}


	public String getDebitDate() {
		return debitDate;
	}


	public void setDebitDate(String debitDate) {
		this.debitDate = debitDate;
	}


	public String getApiRequest() {
		return apiRequest;
	}


	public void setApiRequest(String apiRequest) {
		this.apiRequest = apiRequest;
	}


	

	
	
}
