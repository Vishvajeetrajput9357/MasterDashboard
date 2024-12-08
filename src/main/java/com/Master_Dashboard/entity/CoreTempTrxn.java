package com.Master_Dashboard.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "core_temp_trxn")
public class CoreTempTrxn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "core_temp_trxn_id", nullable = false)
    private Long coreTempTrxnId;

    @Column(name = "transaction_id", nullable = false, length = 36) // if UUID
    private String transactionId;

    @Column(name = "trxn_info", length = 2000)
    private String trxnInfo;

    @Column(name = "app_trxn_info", nullable = false, length = 1000)
    private String appTrxnInfo;

    @Column(name = "retry_count", length = 50) // Change to Integer if it's a numeric value
    private String retryCount;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    public Long getCoreTempTrxnId() {
        return coreTempTrxnId;
    }

    public void setCoreTempTrxnId(Long coreTempTrxnId) {
        this.coreTempTrxnId = coreTempTrxnId;
    }

    public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTrxnInfo() {
        return trxnInfo;
    }

    public void setTrxnInfo(String trxnInfo) {	
        this.trxnInfo = trxnInfo;
    }

    public String getAppTrxnInfo() {
        return appTrxnInfo;
    }

    public void setAppTrxnInfo(String appTrxnInfo) {
        this.appTrxnInfo = appTrxnInfo;
    }

    public String getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(String retryCount) {
        this.retryCount = retryCount;
    }

    public Timestamp getTransactionDate() {
		return transactionDate;
	}

    public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

}
