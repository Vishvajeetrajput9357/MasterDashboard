package com.Master_Dashboard.service;

public interface ENachRequestService {

	long saveENachRequest(String customerMobileNumber, double transactionAmount, String mandateStartDate,
			String mandateEndDate, String customerName, String customerBankAccountNumber, String customerBankIfsc,
			String customerBankName, String customerAccountType, String categoryCode, String frequency, long merchantId,
			String merchantTransactionRefId, String umrn, String debitDate, String apiRequest);

}
