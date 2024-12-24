package com.Master_Dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.Response.EnachTrxnReportResPayload;
import com.Master_Dashboard.Response.MandateResponse;
import com.Master_Dashboard.entity.ENachTransactionDetails;
import com.Master_Dashboard.ex.util.SetErrorResponses;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;
import com.Master_Dashboard.request.ENachTransactionRequest;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.TransactionReportService;

@Service
public class TransactionReportServiceImpl implements TransactionReportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionReportServiceImpl.class);

	private EnachTransactionDetailsRepository enachTransactionDetailsRepository;
	private SetErrorResponses setErrorResponses;

	private TransactionReportServiceImpl(EnachTransactionDetailsRepository enachTransactionDetailsRepository,
			SetErrorResponses setErrorResponses) {
		this.enachTransactionDetailsRepository = enachTransactionDetailsRepository;
		this.setErrorResponses = setErrorResponses;

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
				: (enachTransactionRequest.getServiceName().equalsIgnoreCase("ALL SERVICES"))
						? Arrays.asList(Encryption.encString("MANDATE REGISTRATIONS"),
								Encryption.encString("MANDATE REGISTRATIONS ESIGN"),
								Encryption.encString("DEBIT PRESENTATION"))
						: Collections.singletonList(Encryption.encString(enachTransactionRequest.getServiceName()));

		String mandateId = (null == enachTransactionRequest.getMandateId()
				|| enachTransactionRequest.getMandateId().equalsIgnoreCase("")) ? ""
						: Encryption.encString(enachTransactionRequest.getMandateId());

		Page<ENachTransactionDetails> result = enachTransactionDetailsRepository.findByENachTransactionRequest(
				startDate, endDate, serviceNames, enachTransactionRequest.getStatusId(),
				enachTransactionRequest.getMerchantId(), mandateId, pageable);
		List<EnachTrxnReportResPayload> payloadList = new ArrayList<>();
		LOGGER.info("getSize :  " + result.getSize());

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

	@Override
	public Map<String, Object> enachDeatilByMerchantTrxnId(String merchantTrxnRefId, long merchantId) {

		Map<String, Object> response = new HashMap<String, Object>();
		try {
			LOGGER.info("enachDeatilByMerchantTrxnId 8989:{} ", merchantTrxnRefId);

			Optional<ENachTransactionDetails> mandateRes = enachTransactionDetailsRepository
					.findByMerchantTransactionRefIdAndMerchantId(Encryption.encString(merchantTrxnRefId), merchantId);

			if (mandateRes.isPresent()) {

				ENachTransactionDetails p = mandateRes.get();
				
				LOGGER.info("enachDeatilByMerchantTrxnId 8989:{} ", Encryption.decString(p.getCustomerMobileNumber()));

				MandateResponse mandateResponse = new MandateResponse(p.getMaxAmount(),
						Encryption.decString(p.getFrequency()), p.getMandateStartDate(), p.getMandateEndDate(),
						Encryption.decString(p.getMandateType()), merchantTrxnRefId,
						Encryption.decString(p.getMandateId()), Encryption.decString(p.geteNachUMRN()),
						Encryption.decString(p.getCustomerName()), Encryption.decString(p.getCustomerEmail()),
						Encryption.decString(p.getCustomerMobileNumber()),
						Encryption.decString(p.getCustomerBankAccountNumber()),
						Encryption.decString(p.getCustomerBankIfsc()), Encryption.decString(p.getCustomerBankName()),
						Encryption.decString(p.getCustomerAccountType()),
						Encryption.decString(p.getTransactionStatus()));

				response.put(ResponseMessage.STATUS, ResponseMessage.STATUS_SUCCESS);
				response.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
				response.put(ResponseMessage.DESCRIPTION, ResponseMessage.API_STATUS_SUCCESS);
				response.put("data", mandateResponse);
				return response;
			} else {
				return setErrorResponses.setErrorResponseWith(response, ResponseMessage.FAILED,
						ResponseMessage.MERCHANTTRXNREFID_NOT_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Exception : {}", e.getMessage());
			return setErrorResponses.setApiStatusSomethingWent(response);

		}
	}
}