package com.Master_Dashboard.service;

public interface ENachResponseService {
	
	
	long saveENachRequest(String apiResponse,String merchantTransactionRefId,long merchantId,String mandateId,String umrn,String trxnDate);

}
