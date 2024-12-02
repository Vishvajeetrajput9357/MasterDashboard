package com.Master_Dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import com.Master_Dashboard.Controller.MerchantController;
import com.Master_Dashboard.Response.EnachTrxnReportResPayload;
import com.Master_Dashboard.entity.ENachTransactionDetails;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;
import com.Master_Dashboard.request.ENachTransactionRequest;
import com.Master_Dashboard.service.TransactionReportService;
//import org.modelmapper.ModelMapper;

@Service
public class TransactionReportServiceImpl implements TransactionReportService {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

	private EnachTransactionDetailsRepository enachTransactionDetailsRepository;
//	private ModelMapper modelMapper;

	private TransactionReportServiceImpl(
			EnachTransactionDetailsRepository enachTransactionDetailsRepository/* ,ModelMapper modelMapper */) {
		this.enachTransactionDetailsRepository = enachTransactionDetailsRepository;
//		this.modelMapper=modelMapper;
	}

	@Override
	public List<EnachTrxnReportResPayload> enachTransactionList(ENachTransactionRequest enachTransactionRequest) {

		PageRequest pageable = PageRequest.of(enachTransactionRequest.getPageNo(),
				enachTransactionRequest.getPageSize());

		Page<ENachTransactionDetails> result = enachTransactionDetailsRepository.findByENachTransactionRequest(
				enachTransactionRequest.getStartDate()+" 00:00:00", enachTransactionRequest.getEndDate()+" 23:59:59",
				enachTransactionRequest.getServiceName(), enachTransactionRequest.getStatusId(),
				enachTransactionRequest.getMerchantId(), pageable);
		List<EnachTrxnReportResPayload> payloadList = new ArrayList<>();
		LOGGER.info("eNachTransactionDetails:  "+result.getSize());
		int i = 0;
		for (ENachTransactionDetails eNachTransactionDetails : result.getContent()) {
			i++;
			payloadList.add(convertToDto(eNachTransactionDetails, i + ""));
		}
		return payloadList;
	}

	private EnachTrxnReportResPayload convertToDto(ENachTransactionDetails eNachTransactionDetails, String Number) {

		EnachTrxnReportResPayload payload = new EnachTrxnReportResPayload();
		payload.setsNo(Number);
		payload.seteNachTransactionId(eNachTransactionDetails.geteNachTransactionId());
		payload.setTransactionStatus(eNachTransactionDetails.getTransactionStatus());
		payload.setTransactionDate(eNachTransactionDetails.getTransactionDate() + "");
		payload.setMerchantTransactionRefId(eNachTransactionDetails.getMerchantTransactionRefId());
		payload.setTransactionAmount(eNachTransactionDetails.getTransactionAmount() + "");
		payload.setIsReconcile(eNachTransactionDetails.getIsReconcile());
		payload.setIsSettled(eNachTransactionDetails.getIsSettled());
		payload.setServiceName(eNachTransactionDetails.getServiceName());
		payload.setTrxnRefId(eNachTransactionDetails.getMandateId());
		payload.setMandateId(eNachTransactionDetails.getMerchantId() + "");
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