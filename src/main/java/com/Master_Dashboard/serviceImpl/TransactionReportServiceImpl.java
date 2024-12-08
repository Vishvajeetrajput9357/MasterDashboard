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

import com.Master_Dashboard.Encryption.Encryption;
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
		LOGGER.info("serviceNames " + enachTransactionRequest.getServiceName());

		List<String> serviceNames = (enachTransactionRequest.getServiceName().equalsIgnoreCase("COMPLETE MANDATE"))
				? Arrays.asList(Encryption.encString("MANDATE REGISTRATIONS"),
						Encryption.encString("MANDATE REGISTRATIONS ESIGN"))
				: Collections.singletonList(Encryption.encString(enachTransactionRequest.getServiceName()));

		LOGGER.info("serviceNames " + serviceNames);
		String mandateId = (enachTransactionRequest.getMandateId().equalsIgnoreCase("")) ? ""
				: Encryption.encString(enachTransactionRequest.getMandateId());

		Page<ENachTransactionDetails> result = enachTransactionDetailsRepository.findByENachTransactionRequest(
				startDate, endDate, serviceNames, enachTransactionRequest.getStatusId(),
				enachTransactionRequest.getMerchantId(), mandateId, pageable);
		List<EnachTrxnReportResPayload> payloadList = new ArrayList<>();
		LOGGER.info("result :  " + result.getSize());

		int i = 0;

		for (ENachTransactionDetails eNachTransactionDetails : result.getContent()) {
			i++;
			payloadList.add(convertToDto(eNachTransactionDetails, i + ""));
		}

		LOGGER.info("result :  " + i);
		return payloadList;
	}

	private EnachTrxnReportResPayload convertToDto(ENachTransactionDetails eNachTransactionDetails, String Number) {
		try {
			EnachTrxnReportResPayload payload = new EnachTrxnReportResPayload();
			payload.setsNo(Number);
			payload.setCustomerMobileNumber(Encryption.decString(eNachTransactionDetails.getCustomerMobileNumber()));
			payload.seteNachTransactionId(eNachTransactionDetails.geteNachTransactionId());
			payload.setTransactionStatus(eNachTransactionDetails.getTransactionStatus());
			payload.setTransactionDate(eNachTransactionDetails.getTransactionDate() + "");
			payload.setMerchantTransactionRefId(
					Encryption.decString(eNachTransactionDetails.getMerchantTransactionRefId()));
			payload.setTransactionAmount(eNachTransactionDetails.getTransactionAmount() + "");
			payload.setIsReconcile(eNachTransactionDetails.getIsReconcile());
			payload.setIsSettled(eNachTransactionDetails.getIsSettled());
			payload.setServiceName(Encryption.decString(eNachTransactionDetails.getServiceName()));
			payload.setTrxnRefId(Encryption.decString(eNachTransactionDetails.getMandateId()));
			payload.setMandateId(Encryption.decString(eNachTransactionDetails.getMandateId()));
			payload.setCustomerName(Encryption.decString(eNachTransactionDetails.getCustomerName()));
			payload.seteNachUMRN(Encryption.decString(eNachTransactionDetails.geteNachUMRN()));
			payload.setCharges(eNachTransactionDetails.getMerchantServiceCharge());
			payload.setRemark(Encryption.decString(eNachTransactionDetails.getRemark()));
			payload.setDebitDate(eNachTransactionDetails.getDebitDate());
			payload.setCustomerBankAccountNumber(
					Encryption.decString(eNachTransactionDetails.getCustomerBankAccountNumber()));
			payload.setCustomerBankIfsc(Encryption.decString(eNachTransactionDetails.getCustomerBankIfsc()));
			return payload;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}