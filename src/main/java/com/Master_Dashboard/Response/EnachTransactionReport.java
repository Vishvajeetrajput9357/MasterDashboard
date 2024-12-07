package com.Master_Dashboard.Response;

import java.util.List;

public class EnachTransactionReport<T> {

	private String status;
	private String description;
	private String code;
	private String totalTransaction;
	private String totalFailedTransaction;
	private String totalSuccessTransaction;
	private String totalPendingTransaction;

	private List<T> resPayload;

	public EnachTransactionReport(String status, String description, String code, String totalTransaction,
			String totalFailedTransaction, String totalSuccessTransaction, String totalPendingTransaction,
			List<T> resPayload) {
		this.status = status;
		this.description = description;
		this.code = code;
		this.totalTransaction = totalTransaction;
		this.totalFailedTransaction = totalFailedTransaction;
		this.totalSuccessTransaction = totalSuccessTransaction;
		this.totalPendingTransaction = totalPendingTransaction;
		this.resPayload = resPayload;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}

	public List<T> getResPayload() {
		return resPayload;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setResPayload(List<T> resPayload) {
		this.resPayload = resPayload;
	}

	public String getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(String totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public String getTotalFailedTransaction() {
		return totalFailedTransaction;
	}

	public String getTotalSuccessTransaction() {
		return totalSuccessTransaction;
	}

	public String getTotalPendingTransaction() {
		return totalPendingTransaction;
	}

	public void setTotalFailedTransaction(String totalFailedTransaction) {
		this.totalFailedTransaction = totalFailedTransaction;
	}

	public void setTotalSuccessTransaction(String totalSuccessTransaction) {
		this.totalSuccessTransaction = totalSuccessTransaction;
	}

	public void setTotalPendingTransaction(String totalPendingTransaction) {
		this.totalPendingTransaction = totalPendingTransaction;
	}

}
