package com.Master_Dashboard.serviceImpl;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.ENachRequest;
import com.Master_Dashboard.ex.util.DateAndTime;
import com.Master_Dashboard.repository.ENachRequestRepository;
import com.Master_Dashboard.service.ENachRequestService;

@Service
public class ENachRequestServiceImpl implements ENachRequestService {
	
	@Autowired
	private ENachRequestRepository eNachRequestRepository;

	@Override
	public long saveENachRequest(String customerMobileNumber, double transactionAmount, String mandateStartDate,
			String mandateEndDate, String customerName, String customerBankAccountNumber, String customerBankIfsc,
			String customerBankName, String customerAccountType, String categoryCode, String frequency, long merchantId,
			String merchantTransactionRefId, String umrn, String debitDate, String apiRequest) {
		long res=0;
		try {
			Timestamp tDate = Timestamp.valueOf(DateAndTime.getCurrentTimeInIST());
			
			ENachRequest eNachRequest=new ENachRequest();
			eNachRequest.setApiRequest(apiRequest);
			eNachRequest.setCategoryCode(Encryption.encString(categoryCode));
			eNachRequest.setCustomerAccountType(Encryption.encString(customerAccountType));
			eNachRequest.setCustomerBankAccountNumber(Encryption.encString(customerBankAccountNumber));
			eNachRequest.setCustomerBankIfsc(Encryption.encString(customerBankIfsc));
			eNachRequest.setCustomerBankName(Encryption.encString(customerBankName));
			eNachRequest.setCustomerMobileNumber(customerMobileNumber);
			eNachRequest.setCustomerName(Encryption.encString(customerName));
			eNachRequest.setDebitDate(debitDate);
			eNachRequest.setFrequency(Encryption.encString(frequency));
			eNachRequest.setMandateEndDate(mandateEndDate);
			eNachRequest.setMandateStartDate(mandateStartDate);
			eNachRequest.setMerchantId(merchantId);
			eNachRequest.setMerchantTransactionRefId(Encryption.encString(merchantTransactionRefId));
			eNachRequest.setRequestDate(tDate);
			eNachRequest.setTransactionAmount(transactionAmount);
			eNachRequest.setUmrn(Encryption.encString(umrn));
			eNachRequest=eNachRequestRepository.save(eNachRequest);
			
			res=eNachRequest.getRequestId();
			
		} catch (Exception e) {
			res=0;
		}
		return res;
	}

}
