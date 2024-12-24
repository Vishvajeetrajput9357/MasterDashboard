package com.Master_Dashboard.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.Master_Dashboard.Encryption.Encryption;
import com.Master_Dashboard.entity.ENachTransactionDetails;
import com.Master_Dashboard.ex.util.DateAndTime;
import com.Master_Dashboard.ex.util.GenrateUniqueId;
import com.Master_Dashboard.ex.util.SetErrorResponses;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;
import com.Master_Dashboard.request.CreateMandateRequest;
import com.Master_Dashboard.request.CreatePrasentationRequest;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.CreateNachService;
import com.Master_Dashboard.service.ENachRequestService;
import com.Master_Dashboard.service.ENachTransactionDetailsService;

@Service
public class CreateNachServiceImpl implements CreateNachService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateNachServiceImpl.class);

	private SetErrorResponses setErrorResponses;
	private EnachTransactionDetailsRepository eNachTransactionDetailsRepository;
	private ENachRequestService eNachRequestService;
	private ENachTransactionDetailsService eNachTransactionDetailsService;
	
	

	public CreateNachServiceImpl(SetErrorResponses setErrorResponses,
			EnachTransactionDetailsRepository eNachTransactionDetailsRepository,
			ENachRequestService eNachRequestService, ENachTransactionDetailsService eNachTransactionDetailsService) {
		this.setErrorResponses = setErrorResponses;
		this.eNachTransactionDetailsRepository = eNachTransactionDetailsRepository;
		this.eNachRequestService = eNachRequestService;
		this.eNachTransactionDetailsService = eNachTransactionDetailsService;
	}

	@Override
	public Map<String, Object> createNach(CreateMandateRequest createMandateRequest, long merchantId) {
		LOGGER.info("createMandateRequest data : {}", createMandateRequest.toString());

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (!checkStartDateAndEndDate(createMandateRequest.getMandateStartDate(),
					createMandateRequest.getMandateExpiryDate())) {
				return setErrorResponses.setErrorResponseWith(map, ResponseMessage.FAILED, "Inavlid Mandate date.");
			}

			double transactionAmount = Double.valueOf(createMandateRequest.getMandateDebitAmount());
			String merchantTransactionRefId = GenrateUniqueId.generateUniqueId()+"T"+merchantId;
			String apiRequest = "NA";
			long merchantServiceId = 0;
			String transactionStatus = "PENDING";
			double merchantServiceCharge = 10.0;
			long transactionStatusId = 3;
			
			String serviceName ="";
			if(createMandateRequest.getAuthType().equalsIgnoreCase("Debit")||createMandateRequest.getAuthType().equalsIgnoreCase("NET")) {
				serviceName= "MANDATE REGISTRATIONS";
			}else {
				serviceName= "MANDATE REGISTRATIONS ESIGN";
			}
			String mandateId = GenrateUniqueId.generateUniqueMandateId()+"T"+merchantId;

			long requestId = eNachRequestService.saveENachRequest(createMandateRequest.getCustomerMobile(),
					transactionAmount, createMandateRequest.getMandateStartDate(), createMandateRequest.getMandateExpiryDate(), createMandateRequest.getCustomerName(),
					createMandateRequest.getCustomerAccountNo(), createMandateRequest.getCustomerbankIFSC(),
					createMandateRequest.getBankName(), createMandateRequest.getBankAccountType(),
					createMandateRequest.getDebitType(), createMandateRequest.getMandateDebitFrequency(), merchantId,
					merchantTransactionRefId, "NA", "NA", apiRequest);

			eNachTransactionDetailsService.saveENachTransactionDetails(requestId, 0L,
					createMandateRequest.getCustomerMobile(), createMandateRequest.getCustomerEmailId(), mandateId,
					transactionStatus, createMandateRequest.getAuthType(), createMandateRequest.getMandateStartDate(), createMandateRequest.getMandateExpiryDate(),
					createMandateRequest.getCustomerName(), createMandateRequest.getCustomerAccountNo(),
					createMandateRequest.getCustomerbankIFSC(), createMandateRequest.getBankName(),
					createMandateRequest.getMandateDebitFrequency(), createMandateRequest.getBankAccountType(), "NA",
					merchantId, merchantServiceId, transactionStatus, merchantTransactionRefId, transactionAmount, "NA",
					merchantServiceCharge, 'N', 'N', "Pending at user authentication", serviceName, "NA", transactionStatusId, "HDFC", "NA", "NA",
					createMandateRequest.getDebitType(), "NA",createMandateRequest.getMandateCollectionAmount());
			
			map.put(ResponseMessage.STATUS, ResponseMessage.STATUS_SUCCESS);
			map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
			map.put(ResponseMessage.DESCRIPTION, ResponseMessage.MANDATE_URL_SUCCESS);
			map.put("url", "https://empirical-tootsie-kjsstpay-6c9fa64e.koyeb.app/dashboard/nachRedirect/createMandate/"+merchantTransactionRefId);
//			map.put("url", "http://localhost:8090/dashboard/nachRedirect/createMandate/"+merchantTransactionRefId);
			return map;
			
		} catch (Exception e) {	
			return setErrorResponses.setErrorResponseWith(map, ResponseMessage.SOMETHING_WENT_WRONG, ResponseMessage.SOMETHING_WENT_WRONG_DESCRIPTION);
		}
	}
	
	
	@Override
	public Map<String, Object> createPrasentation(CreatePrasentationRequest createPrasentationRequest,
			long merchantId) {
		LOGGER.info("createPrasentation data : {}", createPrasentationRequest.toString());

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Optional<ENachTransactionDetails> nachTransactionDetails=	eNachTransactionDetailsRepository.findByMerchantTransactionRefId(Encryption.encString(createPrasentationRequest.getMandateId()));
			if (!nachTransactionDetails.isPresent()) {
				 return setErrorResponses.setErrorResponseWith(map, ResponseMessage.FAILED, "Debit presentation not allowed.");
			}
			ENachTransactionDetails enachTransactionDetail=nachTransactionDetails.get();
			
			if ("PENDING".equalsIgnoreCase(enachTransactionDetail.getTransactionStatus())||"FAILED".equalsIgnoreCase(enachTransactionDetail.getTransactionStatus()) || !(enachTransactionDetail.getMerchantId()==merchantId)) {
				 return setErrorResponses.setErrorResponseWith(map, ResponseMessage.FAILED, "Debit Presentation not allowed for pending or failed mandate Registration.");
			}
			
			/*
			 * if (createPrasentationRequest.getSettlementDate().equalsIgnoreCase(
			 * enachTransactionDetail.getDebitDate()) &&
			 * Double.parseDouble(createPrasentationRequest.getAmount())==
			 * enachTransactionDetail.getTransactionAmount()) {
			 * System.out.println("jkhdkahs"); return
			 * setErrorResponses.setErrorResponseWith(map, ResponseMessage.FAILED,
			 * "Duplicate debit presentation is not allowed on the same settlement date.");
			 * }
			 */
			
			
			double transactionAmount = Double.valueOf(createPrasentationRequest.getAmount());
			String merchantTransactionRefId = GenrateUniqueId.generateUniqueId()+"T"+merchantId;
			String apiRequest = "NA";
			long merchantServiceId = 0;
			String transactionStatus = "PENDING";
			double merchantServiceCharge = 7.0;
			long transactionStatusId = 3;
			String remark="Prasentation_intiated";
			String serviceName ="DEBIT PRESENTATION";
			String mandateId = createPrasentationRequest.getMandateId();

			
			long requestId = eNachRequestService.saveENachRequest(Encryption.decString(enachTransactionDetail.getCustomerMobileNumber()),
					transactionAmount, enachTransactionDetail.getMandateStartDate(), enachTransactionDetail.getMandateEndDate(), Encryption.decString(enachTransactionDetail.getCustomerName()),
					enachTransactionDetail.getCustomerBankAccountNumber(), Encryption.decString(enachTransactionDetail.getCustomerBankIfsc()),
					Encryption.decString(enachTransactionDetail.getCustomerBankName()), Encryption.decString(enachTransactionDetail.getCustomerAccountType()),
					"NA","NA", merchantId,
					merchantTransactionRefId, Encryption.decString(enachTransactionDetail.geteNachUMRN()), enachTransactionDetail.getDebitDate(), apiRequest);
			
			eNachTransactionDetailsService.saveENachTransactionDetails(requestId, 0L,
					Encryption.decString(enachTransactionDetail.getCustomerMobileNumber()), Encryption.decString(enachTransactionDetail.getCustomerEmail()), mandateId,
					transactionStatus, "NA", enachTransactionDetail.getMandateStartDate(), enachTransactionDetail.getMandateEndDate(),
					Encryption.decString(enachTransactionDetail.getCustomerName()), Encryption.decString(enachTransactionDetail.getCustomerBankAccountNumber()),
					Encryption.decString(enachTransactionDetail.getCustomerBankIfsc()), Encryption.decString(enachTransactionDetail.getCustomerBankName()),
					"NA", Encryption.decString(enachTransactionDetail.getCustomerAccountType()), "NA",
					merchantId, merchantServiceId, transactionStatus, merchantTransactionRefId, transactionAmount, "NA",
					merchantServiceCharge, 'N', 'N', remark, serviceName, "NA", transactionStatusId, "HDFC", "NA", "NA",
					"NA", createPrasentationRequest.getSettlementDate(),"0");
			
			map.put(ResponseMessage.STATUS, ResponseMessage.STATUS_SUCCESS);
			map.put(ResponseMessage.CODE, ResponseMessage.SUCCESS);
			map.put(ResponseMessage.DESCRIPTION, ResponseMessage.MANDATE_URL_SUCCESS);
			return map;

		} catch (Exception e) {	
			e.printStackTrace();
			return setErrorResponses.setErrorResponseWith(map, ResponseMessage.SOMETHING_WENT_WRONG, ResponseMessage.SOMETHING_WENT_WRONG_DESCRIPTION);
		}
	}
	
	public boolean checkStartDateAndEndDate(String startCollectionDate, String lastCollectionDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date aDate = sdf.parse(startCollectionDate);
			Date bDate = sdf.parse(lastCollectionDate);
			Date currentDate = sdf.parse(DateAndTime.getCurrentTimeInIST());

			return aDate.compareTo(currentDate) >= 0 && bDate.compareTo(currentDate) >= 0
					&& aDate.compareTo(bDate) <= 0;
		} catch (Exception e) {
			return false;
		}
	}


}