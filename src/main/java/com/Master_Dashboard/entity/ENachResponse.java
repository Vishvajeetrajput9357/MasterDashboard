package com.Master_Dashboard.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ENACH_RESPONSE")
@EntityListeners(AuditingEntityListener.class)
public class ENachResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESPONSE_ID")
	private Long responsetId;

	@Column(name = "RESPONSE_DATE", nullable = false)
	private Timestamp responseDate;

	@Column(name = "API_RESPONSE", length = 1500, nullable = false)
	private String apiResponse;

	@Column(name = "MERCHANT_TRANSACTION_REF_ID", length = 100, nullable = false)
	private String merchantTransactionRefId;

	@Column(name = "MERCHANT_ID", nullable = false)
	private long merchantId;

	@Column(name = "MANDATE_ID", length = 100, nullable = false)
	private String mandateId="NA";

	@Column(name = "UMRN", length = 100, nullable = false)
	private String umrn="NA";
	
	@Column(name = "TRXN_DATE", length = 100, nullable = false)
	private String trxnDate="NA";

	public Long getResponsetId() {
		return responsetId;
	}

	public void setResponsetId(Long responsetId) {
		this.responsetId = responsetId;
	}

	public Timestamp getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Timestamp responseDate) {
		this.responseDate = responseDate;
	}

	public String getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(String apiResponse) {
		this.apiResponse = apiResponse;
	}

	public String getMerchantTransactionRefId() {
		return merchantTransactionRefId;
	}

	public void setMerchantTransactionRefId(String merchantTransactionRefId) {
		this.merchantTransactionRefId = merchantTransactionRefId;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMandateId() {
		return mandateId;
	}

	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}

	public String getUmrn() {
		return umrn;
	}

	public void setUmrn(String umrn) {
		this.umrn = umrn;
	}

	public String getTrxnDate() {
		return trxnDate;
	}

	public void setTrxnDate(String trxnDate) {
		this.trxnDate = trxnDate;
	}


	
}
