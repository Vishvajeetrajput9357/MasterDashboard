package com.Master_Dashboard.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ENachTransactionRequest {

	private String startDate;
	
	private String endDate;
	
	@Value("10")
	private Integer pageSize;

	@Value("0")
	private Integer pageNo;
	
	@Pattern(regexp = "COMPLETE MANDATE|MANDATE REGISTRATIONS ESIGN|MANDATE REGISTRATIONS|DEBIT PRESENTATION|ALL SERVICES", message = "Invalid Service Name COMPLETE MANDATE|MANDATE REGISTRATIONS ESIGN|MANDATE REGISTRATIONS|DEBIT PRESENTATION|ALL SERVICES")
	private String serviceName;

	@Value("0")
	private Long statusId;
	
	@JsonIgnore
	private Long merchantId;
	
	@Value("")
	private String mandateId;
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMandateId() {
		return mandateId;
	}


	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}


	@Override
	public String toString() {
		return "ENachTransactionRequest [startDate=" + startDate + ", endDate=" + endDate + ", pageSize=" + pageSize
				+ ", pageNo=" + pageNo + ", serviceName=" + serviceName + ", statusId=" + statusId + ", merchantId="
				+ merchantId + ", mandateId=" + mandateId + "]";
	}

	


	
	
}
