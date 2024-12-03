package com.Master_Dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import com.Master_Dashboard.Response.EnachTrxnReportResPayload;
import com.Master_Dashboard.entity.ENachTransactionDetails;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;
import com.Master_Dashboard.request.ENachTransactionRequest;
import com.Master_Dashboard.service.TransactionReportService;
//import org.modelmapper.ModelMapper;

@Service
public class TransactionReportServiceImpl implements TransactionReportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionReportServiceImpl.class);

	private EnachTransactionDetailsRepository enachTransactionDetailsRepository;

	private TransactionReportServiceImpl(
			EnachTransactionDetailsRepository enachTransactionDetailsRepository/* ,ModelMapper modelMapper */) {
		this.enachTransactionDetailsRepository = enachTransactionDetailsRepository;
//		this.modelMapper=modelMapper;
	}

	@Override
	public List<EnachTrxnReportResPayload> enachTransactionList(ENachTransactionRequest enachTransactionRequest) {

		PageRequest pageable = PageRequest.of(enachTransactionRequest.getPageNo(),
				enachTransactionRequest.getPageSize());

		String startDate = enachTransactionRequest.getStartDate();
		String endDate = enachTransactionRequest.getEndDate();

		if (enachTransactionRequest.getStartDate().equalsIgnoreCase("")
				|| enachTransactionRequest.getEndDate().equalsIgnoreCase("")) {
			startDate = null;
			endDate = null;
		} else {
			startDate = startDate + " 00:00:00";
			endDate = endDate + " 23:59:59";
		}
		
		List<String> serviceNames = (enachTransactionRequest.getServiceName().equalsIgnoreCase("COMPLETE MANDATE"))
						? Arrays.asList("MANDATE REGISTRATIONS", "MANDATE REGISTRATIONS ESIGN")
						:  Collections.singletonList(enachTransactionRequest.getServiceName());
		
			
		LOGGER.info("serviceNames2 "+serviceNames);
		
		Page<ENachTransactionDetails> result = enachTransactionDetailsRepository.findByENachTransactionRequest(
				startDate,
				endDate, serviceNames,
				enachTransactionRequest.getStatusId(), enachTransactionRequest.getMerchantId(),
				enachTransactionRequest.getMandateId(), pageable);
		List<EnachTrxnReportResPayload> payloadList = new ArrayList<>();
		LOGGER.info("result :  " + result.getSize());
		
		int i = 0;
		for (ENachTransactionDetails eNachTransactionDetails : result.getContent()) {
			i++;
			payloadList.add(convertToDto(eNachTransactionDetails, i + ""));
		}
		
		LOGGER.info("result :  " +i);

		return payloadList;
	}

	private EnachTrxnReportResPayload convertToDto(ENachTransactionDetails eNachTransactionDetails, String Number) {

		EnachTrxnReportResPayload payload = new EnachTrxnReportResPayload();
		payload.setsNo(Number);
		payload.setCustomerMobileNumber(eNachTransactionDetails.getCustomerMobileNumber());
		payload.seteNachTransactionId(eNachTransactionDetails.geteNachTransactionId());
		payload.setTransactionStatus(eNachTransactionDetails.getTransactionStatus());
		payload.setTransactionDate(eNachTransactionDetails.getTransactionDate() + "");
		payload.setMerchantTransactionRefId(eNachTransactionDetails.getMerchantTransactionRefId());
		payload.setTransactionAmount(eNachTransactionDetails.getTransactionAmount() + "");
		payload.setIsReconcile(eNachTransactionDetails.getIsReconcile());
		payload.setIsSettled(eNachTransactionDetails.getIsSettled());
		payload.setServiceName(eNachTransactionDetails.getServiceName());
		payload.setTrxnRefId(eNachTransactionDetails.getMandateId());
		payload.setMandateId(eNachTransactionDetails.getMandateId());
		payload.setCustomerName(eNachTransactionDetails.getCustomerName());
		payload.seteNachUMRN(eNachTransactionDetails.geteNachUMRN());
		payload.setCharges(eNachTransactionDetails.getMerchantServiceCharge());
		payload.setRemark(eNachTransactionDetails.getRemark());
		payload.setDebitDate(eNachTransactionDetails.getDebitDate());
		payload.setCustomerBankAccountNumber(eNachTransactionDetails.getCustomerBankAccountNumber());
		payload.setCustomerBankIfsc(eNachTransactionDetails.getCustomerBankIfsc());
		return payload;
	}
}