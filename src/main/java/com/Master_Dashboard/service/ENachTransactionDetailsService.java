package com.Master_Dashboard.service;

public interface ENachTransactionDetailsService {

	long saveENachTransactionDetails(long requestId, long responseId, String customerMobileNumber, String customerEmail,
			String mandateId, String apiStatus, String mandateType, String mandateStartDate, String mandateEndDate,
			String customerName, String customerBankAccountNumber, String customerBankIfsc, String customerBankName,
			String frequency, String customerAccountType, String instrumentType, long merchantId,
			long merchantServiceId, String transactionStatus, String merchantTransactionRefId, double transactionAmount,
			String eNachId, double merchantServiceCharge, char isReconcile, char isSettled, String remark,
			String serviceName, String trxnRefId, long transactionStatusId, String serviceProviderName,
			String serviceProviderUtilityCode, String eNachUMRN, String categoryCode, String debitDate,String maxAmount);

}
