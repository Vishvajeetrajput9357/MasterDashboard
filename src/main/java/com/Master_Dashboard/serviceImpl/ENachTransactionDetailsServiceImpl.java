package com.Master_Dashboard.serviceImpl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.ENachTransactionDetails;
import com.Master_Dashboard.ex.util.DateAndTime;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;
import com.Master_Dashboard.service.ENachTransactionDetailsService;

@Service
public class ENachTransactionDetailsServiceImpl implements ENachTransactionDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ENachTransactionDetailsServiceImpl.class);

	@Autowired
	private EnachTransactionDetailsRepository eNachTransactionDetailsRepository;

	@Override
	public long saveENachTransactionDetails(long requestId, long responseId, String customerMobileNumber,
			String customerEmail, String mandateId, String apiStatus, String mandateType, String mandateStartDate,
			String mandateEndDate, String customerName, String customerBankAccountNumber, String customerBankIfsc,
			String customerBankName, String frequency, String customerAccountType, String instrumentType,
			long merchantId, long merchantServiceId, String transactionStatus, String merchantTransactionRefId,
			double transactionAmount, String eNachId, double merchantServiceCharge, char isReconcile, char isSettled,
			String remark, String serviceName, String trxnRefId, long transactionStatusId, String serviceProviderName,
			String serviceProviderUtilityCode, String eNachUMRN, String categoryCode, String debitDate,String maxAmount) {
		long res=0;
		try {
			Timestamp tDate = Timestamp.valueOf(DateAndTime.getCurrentTimeInIST());
			ENachTransactionDetails eNachTransactionDetails=new ENachTransactionDetails();
			eNachTransactionDetails.setApiStatus(apiStatus);
			eNachTransactionDetails.setCategoryCode(Encryption.encString(categoryCode));
			eNachTransactionDetails.setCustomerAccountType(Encryption.encString(customerAccountType));
			eNachTransactionDetails.setCustomerBankAccountNumber(Encryption.encString(customerBankAccountNumber));
			eNachTransactionDetails.setCustomerBankIfsc(Encryption.encString(customerBankIfsc));
			eNachTransactionDetails.setCustomerBankName(Encryption.encString(customerBankName));
			eNachTransactionDetails.setCustomerEmail(Encryption.encString(customerEmail));
			eNachTransactionDetails.setCustomerMobileNumber(Encryption.encString(customerMobileNumber));
			eNachTransactionDetails.setCustomerName(Encryption.encString(customerName));
			eNachTransactionDetails.setDebitDate(debitDate);
			eNachTransactionDetails.seteNachId(Encryption.encString(eNachId));
			eNachTransactionDetails.seteNachUMRN(Encryption.encString(eNachUMRN));
			eNachTransactionDetails.setFrequency(Encryption.encString(frequency));
			eNachTransactionDetails.setInstrumentType(Encryption.encString(instrumentType));
			eNachTransactionDetails.setIsReconcile('0');
			eNachTransactionDetails.setIsSettled('0');
			eNachTransactionDetails.setMandateCancellationDate(tDate);
			eNachTransactionDetails.setMandateEndDate(mandateEndDate);
			eNachTransactionDetails.setMandateId(Encryption.encString(mandateId));
			eNachTransactionDetails.setMandateStartDate(mandateStartDate);
			eNachTransactionDetails.setMandateType(Encryption.encString(mandateType));
			eNachTransactionDetails.setMerchantId(merchantId);
			eNachTransactionDetails.setMerchantServiceCharge(merchantServiceCharge);
			eNachTransactionDetails.setMerchantServiceId(merchantServiceId);
			eNachTransactionDetails.setMerchantTransactionRefId(Encryption.encString(merchantTransactionRefId));
			eNachTransactionDetails.setRemark(Encryption.encString(remark));
			eNachTransactionDetails.setRequestId(requestId);
			eNachTransactionDetails.setResponseId(responseId);
			eNachTransactionDetails.setServiceName(Encryption.encString(serviceName));
			eNachTransactionDetails.setServiceProviderName(Encryption.encString(serviceProviderName));
			eNachTransactionDetails.setServiceProviderUtilityCode(Encryption.encString(serviceProviderUtilityCode));
			eNachTransactionDetails.setTransactionAmount(transactionAmount);
			eNachTransactionDetails.setTransactionDate(tDate);
			eNachTransactionDetails.setTransactionStatus(transactionStatus);
			eNachTransactionDetails.setTransactionStatusId(transactionStatusId);
			eNachTransactionDetails.setTransactionUpdateDate("NA");
			eNachTransactionDetails.setMaxAmount(maxAmount);
			eNachTransactionDetails.setTrxnRefId(Encryption.encString(trxnRefId));
			
			LOGGER.info("Mandate detials : "+eNachTransactionDetails.toString());
			eNachTransactionDetails=eNachTransactionDetailsRepository.save(eNachTransactionDetails);
			
			res=eNachTransactionDetails.geteNachTransactionId();
		} catch (Exception e) {
			res=0;
		}
		return res;
	}

}
