package com.Master_Dashboard.Response;

import java.util.List;

public class EnachTransactionReport<T> {

	private String status;
	private String description;
	private String code;
	private String totalTransaction;
	private List<T> resPayload;

	
	public EnachTransactionReport(String status, String description, String code, String totalTransaction,List<T> resPayload) {
		this.status = status;
		this.description = description;
		this.code = code;
		this.resPayload = resPayload;
		this.totalTransaction=totalTransaction;
		
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

	
	
}
