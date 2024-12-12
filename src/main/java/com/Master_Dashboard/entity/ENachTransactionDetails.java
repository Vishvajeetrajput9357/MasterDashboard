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
@Table(name = "ENACH_TRANSACTION_DETAILS")
@EntityListeners(AuditingEntityListener.class)
public class ENachTransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENACH_TRANSACTION_ID")
	private Long eNachTransactionId;

	@Column(name = "REQUEST_ID")
	private Long requestId;

	@Column(name = "RESPONSE_ID")
	private Long responseId;

	@Column(name = "CUSTOMER_MOBILE_NO", length = 100, nullable = false)
	private String customerMobileNumber;

	@Column(name = "CUSTOMER_EMAIL", length = 100, nullable = false)
	private String customerEmail;

	@Column(name = "MANDATE_ID", length = 100, nullable = false)
	private String mandateId;

	@Column(name = "API_STATUS", length = 100, nullable = false)
	private String apiStatus;

	@Column(name = "MANDATE_TYPE", length = 100, nullable = false)
	private String mandateType;

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

	@Column(name = "FREQUENCY", length = 100, nullable = false)
	private String frequency;

	@Column(name = "CUSTOMER_ACCOUNT_TYPE", length = 100, nullable = false)
	private String customerAccountType;

	@Column(name = "INSTRUMENT_TYPE", length = 100, nullable = false)
	private String instrumentType;

	@Column(name = "MERCHANT_ID", nullable = false)
	private long merchantId;

	@Column(name = "MERCHANT_SERVICE_ID", nullable = false)
	private long merchantServiceId;

	@Column(name = "TRANSACTION_STATUS", length = 100, nullable = false)
	private String transactionStatus;

	@Column(name = "TRANSACTION_DATE", nullable = false)
	private Timestamp transactionDate;

	@Column(name = "TRANSACTION_UPDATE_DATE", nullable = false)
	private String transactionUpdateDate;

	@Column(name = "MERCHANT_TRANSACTION_REF_ID", length = 200, nullable = false)
	private String merchantTransactionRefId;

	@Column(name = "TRANSACTION_AMOUNT", nullable = false)
	private double transactionAmount;

	@Column(name = "ENACH_ID", length = 100, nullable = false)
	private String eNachId;

	@Column(name = "MERCHANT_SERVICE_CHARGE", nullable = false)
	private double merchantServiceCharge;

	@Column(name = "IS_RECONCILE", length = 1, nullable = false)
	private char isReconcile;

	@Column(name = "IS_SETTLED", length = 1, nullable = false)
	private char isSettled;

	@Column(name = "REMARK", length = 300, nullable = false)
	private String remark;

	@Column(name = "SERVICE_NAME", length = 200)
	private String serviceName;

	@Column(name = "TRXN_REF_ID", length = 200)
	private String trxnRefId;

	@Column(name = "TRANSACTION_STATUS_ID")
	private long transactionStatusId;

	@Column(name = "MANDATE_CANCELLATION_DATE", nullable = false)
	private Timestamp mandateCancellationDate;

	@Column(name = "SERVICE_PROVIDER_NAME", nullable = false)
	private String serviceProviderName;

	@Column(name = "SERVICE_PROVIDER_UTILITY_CODE", nullable = false)
	private String serviceProviderUtilityCode;

	@Column(name = "ENACH_UMRN", nullable = false)
	private String eNachUMRN;

	@Column(name = "CATEGORY_CODE", nullable = false)
	private String categoryCode = "NA";

	@Column(name = "DEBIT_DATE", nullable = false)
	private String debitDate = "NA";

	@Column(name = "MAX_AMOUNT", nullable = false)
	private String maxAmount = "NA";

	public Long geteNachTransactionId() {
		return eNachTransactionId;
	}

	public void seteNachTransactionId(Long eNachTransactionId) {
		this.eNachTransactionId = eNachTransactionId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getResponseId() {
		return responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getMandateId() {
		return mandateId;
	}

	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}

	public String getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}

	public String getMandateType() {
		return mandateType;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCustomerAccountType() {
		return customerAccountType;
	}

	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantServiceId() {
		return merchantServiceId;
	}

	public void setMerchantServiceId(long merchantServiceId) {
		this.merchantServiceId = merchantServiceId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionUpdateDate() {
		return transactionUpdateDate;
	}

	public void setTransactionUpdateDate(String transactionUpdateDate) {
		this.transactionUpdateDate = transactionUpdateDate;
	}

	public String getMerchantTransactionRefId() {
		return merchantTransactionRefId;
	}

	public void setMerchantTransactionRefId(String merchantTransactionRefId) {
		this.merchantTransactionRefId = merchantTransactionRefId;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String geteNachId() {
		return eNachId;
	}

	public void seteNachId(String eNachId) {
		this.eNachId = eNachId;
	}

	public double getMerchantServiceCharge() {
		return merchantServiceCharge;
	}

	public void setMerchantServiceCharge(double merchantServiceCharge) {
		this.merchantServiceCharge = merchantServiceCharge;
	}

	public char getIsReconcile() {
		return isReconcile;
	}

	public void setIsReconcile(char isReconcile) {
		this.isReconcile = isReconcile;
	}

	public char getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(char isSettled) {
		this.isSettled = isSettled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTrxnRefId() {
		return trxnRefId;
	}

	public void setTrxnRefId(String trxnRefId) {
		this.trxnRefId = trxnRefId;
	}

	public long getTransactionStatusId() {
		return transactionStatusId;
	}

	public void setTransactionStatusId(long transactionStatusId) {
		this.transactionStatusId = transactionStatusId;
	}

	public Timestamp getMandateCancellationDate() {
		return mandateCancellationDate;
	}

	public void setMandateCancellationDate(Timestamp mandateCancellationDate) {
		this.mandateCancellationDate = mandateCancellationDate;
	}

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public String getServiceProviderUtilityCode() {
		return serviceProviderUtilityCode;
	}

	public void setServiceProviderUtilityCode(String serviceProviderUtilityCode) {
		this.serviceProviderUtilityCode = serviceProviderUtilityCode;
	}

	public String geteNachUMRN() {
		return eNachUMRN;
	}

	public void seteNachUMRN(String eNachUMRN) {
		this.eNachUMRN = eNachUMRN;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDebitDate() {
		return debitDate;
	}

	public void setDebitDate(String debitDate) {
		this.debitDate = debitDate;
	}

	@Override
	public String toString() {
		return "ENachTransactionDetails [eNachTransactionId=" + eNachTransactionId + ", requestId=" + requestId
				+ ", responseId=" + responseId + ", customerMobileNumber=" + customerMobileNumber + ", customerEmail="
				+ customerEmail + ", mandateId=" + mandateId + ", apiStatus=" + apiStatus + ", mandateType="
				+ mandateType + ", mandateStartDate=" + mandateStartDate + ", mandateEndDate=" + mandateEndDate
				+ ", customerName=" + customerName + ", customerBankAccountNumber=" + customerBankAccountNumber
				+ ", customerBankIfsc=" + customerBankIfsc + ", customerBankName=" + customerBankName + ", frequency="
				+ frequency + ", customerAccountType=" + customerAccountType + ", instrumentType=" + instrumentType
				+ ", merchantId=" + merchantId + ", merchantServiceId=" + merchantServiceId + ", transactionStatus="
				+ transactionStatus + ", transactionDate=" + transactionDate + ", transactionUpdateDate="
				+ transactionUpdateDate + ", merchantTransactionRefId=" + merchantTransactionRefId
				+ ", transactionAmount=" + transactionAmount + ", eNachId=" + eNachId + ", merchantServiceCharge="
				+ merchantServiceCharge + ", isReconcile=" + isReconcile + ", isSettled=" + isSettled + ", remark="
				+ remark + ", serviceName=" + serviceName + ", trxnRefId=" + trxnRefId + ", transactionStatusId="
				+ transactionStatusId + ", mandateCancellationDate=" + mandateCancellationDate
				+ ", serviceProviderName=" + serviceProviderName + ", serviceProviderUtilityCode="
				+ serviceProviderUtilityCode + ", eNachUMRN=" + eNachUMRN + ", categoryCode=" + categoryCode
				+ ", debitDate=" + debitDate + "]";
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

}
