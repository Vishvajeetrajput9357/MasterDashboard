package com.Master_Dashboard.service;

import java.util.List;
import com.Master_Dashboard.Response.EnachTrxnReportResPayload;
import com.Master_Dashboard.request.ENachTransactionRequest;

public interface TransactionReportService {

	List<EnachTrxnReportResPayload> enachTransactionList(ENachTransactionRequest enachTransactionRequest);


}
