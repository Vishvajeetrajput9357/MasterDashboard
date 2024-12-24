package com.Master_Dashboard.serviceImpl;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.ENachResponse;
import com.Master_Dashboard.ex.util.DateAndTime;
import com.Master_Dashboard.repository.ENachResponseRepository;
import com.Master_Dashboard.service.ENachResponseService;

@Service
public class ENachResponseServiceImpl implements ENachResponseService {

	@Autowired
	private ENachResponseRepository eNachResponseRepository; 
	
	@Override
	public long saveENachRequest(String apiResponse, String merchantTransactionRefId, long merchantId, String mandateId,
			String umrn, String trxnDate) {
	long res=0;
	try {
		Timestamp tDate = Timestamp.valueOf(DateAndTime.getCurrentTimeInIST());
		
		ENachResponse eNachResponse=new ENachResponse();
		eNachResponse.setApiResponse(apiResponse);
		eNachResponse.setMandateId(Encryption.encString(mandateId));
		eNachResponse.setMerchantId(merchantId);
		eNachResponse.setMerchantTransactionRefId(Encryption.encString(merchantTransactionRefId));
		eNachResponse.setResponseDate(tDate);
		eNachResponse.setTrxnDate(trxnDate);
		eNachResponse.setUmrn(Encryption.encString(umrn));
		eNachResponse=eNachResponseRepository.save(eNachResponse);
		
		res=eNachResponse.getResponsetId();
		
		
	} catch (Exception e) {
		res=0;
	}
		return res;
	}

}
