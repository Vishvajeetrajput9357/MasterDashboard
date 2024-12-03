package com.Master_Dashboard.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Master_Dashboard.Response.EnachTransactionReport;
import com.Master_Dashboard.Response.EnachTrxnReportResPayload;
import com.Master_Dashboard.entity.MerchantInfo;
import com.Master_Dashboard.ex.util.SetErrorResponses;
import com.Master_Dashboard.repository.EnachTransactionDetailsRepository;
import com.Master_Dashboard.request.ENachTransactionRequest;
import com.Master_Dashboard.request.ResponseMessage;
import com.Master_Dashboard.service.TransactionReportService;

@RestController
@RequestMapping("/report")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class TransactionReportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionReportController.class);

	private TransactionReportService transactionReportService;
	private SetErrorResponses setErrorResponses;
	private EnachTransactionDetailsRepository enachTransactionDetailsRepository;

	public TransactionReportController(TransactionReportService transactionReportService,
			SetErrorResponses setErrorResponses, EnachTransactionDetailsRepository enachTransactionDetailsRepository) {
		this.transactionReportService = transactionReportService;
		this.setErrorResponses = setErrorResponses;
		this.enachTransactionDetailsRepository = enachTransactionDetailsRepository;
	}

	@PostMapping("/EnachTransactionReport")
	public EnachTransactionReport<EnachTrxnReportResPayload> enachTransactionReport(
			@RequestHeader("Client-Id") String clientId, @RequestHeader("Client-Secret") String clientSecret,
			@Valid @RequestBody ENachTransactionRequest enachTransactionRequest) {

		try {
			Optional<MerchantInfo> merchantInfoOpt = setErrorResponses.validateMerchant(clientId, clientSecret);
			MerchantInfo merchantInfo = merchantInfoOpt.get();
			if (!merchantInfoOpt.isPresent() || !(merchantInfo.getIsMerchantActive().equalsIgnoreCase("Y")
					&& merchantInfo.getIsMerchantActive().equalsIgnoreCase("Y"))) {
				return new EnachTransactionReport<>(ResponseMessage.API_STATUS_FAILED, "Failed to fetch data",
						ResponseMessage.UNAUTHORISED_DESCRIPTION, "NA", null);
			}
			enachTransactionRequest.setMerchantId(merchantInfo.getMerchantId());
			LOGGER.info(enachTransactionRequest.toString());

			List<EnachTrxnReportResPayload> data = transactionReportService
					.enachTransactionList(enachTransactionRequest);
			if (data == null || data.isEmpty()) {
				return new EnachTransactionReport<>(ResponseMessage.API_STATUS_FAILED, "Failed to fetch data",
						ResponseMessage.FAILED, "NA", null);
			} else {
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

				List<String> serviceNames = (enachTransactionRequest.getServiceName()
						.equalsIgnoreCase("COMPLETE MANDATE"))
								? Arrays.asList("MANDATE REGISTRATIONS", "MANDATE REGISTRATIONS ESIGN")
								: Collections.singletonList(enachTransactionRequest.getServiceName());

				return new EnachTransactionReport<>(ResponseMessage.API_STATUS_SUCCESS,
						ResponseMessage.ENACH_TRANSACTION_LIST, ResponseMessage.SUCCESS,
						enachTransactionDetailsRepository.findTotalENachTransactionRequest(startDate, endDate,
								serviceNames, enachTransactionRequest.getStatusId(),
								enachTransactionRequest.getMerchantId(), enachTransactionRequest.getMandateId()) + "",data);
			}
		} catch (Exception e) {
			LOGGER.info("Exception : {}",e.getMessage());
			return new EnachTransactionReport<>(ResponseMessage.API_STATUS_FAILED, "Failed to fetch data",
					ResponseMessage.SOMETHING_WENT_WRONG, "NA", null);
		}
	}

}