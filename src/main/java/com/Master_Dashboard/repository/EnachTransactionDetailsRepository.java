package com.Master_Dashboard.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Master_Dashboard.entity.ENachTransactionDetails;

@Repository
public interface EnachTransactionDetailsRepository extends JpaRepository<ENachTransactionDetails, Long> {

	@Query(value = "SELECT * " +
            "FROM enach_transaction_details p " +
            "WHERE (:startDate IS NULL OR :endDate IS NULL OR p.transaction_date BETWEEN :startDate AND :endDate) " +
            "  AND (COALESCE(:statusId, '') = '' OR p.transaction_status_id = COALESCE(NULLIF(:statusId, ''), p.transaction_status_id)) " +
            "  AND (COALESCE(:merchantId, '') = '' OR p.merchant_id = COALESCE(NULLIF(:merchantId, ''), p.merchant_id)) " +
            "  AND (COALESCE(:serviceName, '') = '' OR p.service_name IN (:serviceName))" +
            "  AND (COALESCE(:mandateId, '') = '' OR p.mandate_Id = COALESCE(NULLIF(:mandateId, ''), p.mandate_Id))"+
            "ORDER BY p.enach_transaction_id DESC",
    nativeQuery = true) 
	Page<ENachTransactionDetails> findByENachTransactionRequest(
	    @Param("startDate") String startDate,
	    @Param("endDate") String endDate,
	    @Param("serviceName") List<String> serviceName,
	    @Param("statusId") Long statusId,
	    @Param("merchantId") Long merchantId,
	    @Param("mandateId") String mandateId,
	    Pageable pageable 
	);
	
	@Query(value = "SELECT count(*) " +
            "FROM enach_transaction_details p " +
            "WHERE (:startDate IS NULL OR :endDate IS NULL OR p.transaction_date BETWEEN :startDate AND :endDate) " +
            "  AND (COALESCE(:statusId, '') = '' OR p.transaction_status_id = COALESCE(NULLIF(:statusId, ''), p.transaction_status_id)) " +
            "  AND (COALESCE(:merchantId, '') = '' OR p.merchant_id = COALESCE(NULLIF(:merchantId, ''), p.merchant_id)) " +
            "  AND (COALESCE(:serviceName, '') = '' OR p.service_name IN (:serviceName))" +
            "  AND (COALESCE(:mandateId, '') = '' OR p.mandate_Id = COALESCE(NULLIF(:mandateId, ''), p.mandate_Id))",
    nativeQuery = true) 
	int findTotalENachTransactionRequest(
	    @Param("startDate") String startDate,
	    @Param("endDate") String endDate,
	    @Param("serviceName") List<String> serviceName,
	    @Param("statusId") Long statusId,
	    @Param("merchantId") Long merchantId,
	    @Param("mandateId") String mandateId
	);
		
	Optional<ENachTransactionDetails> findByMerchantTransactionRefId(String merchantTransactionRefId);

	Optional<ENachTransactionDetails> findByMandateId(String mandateId);
	
	@Query(value = "SELECT * FROM enach_transaction_details WHERE merchant_transaction_ref_id=?1 AND merchant_id=?2", nativeQuery = true)
	Optional<ENachTransactionDetails> findByMerchantTransactionRefIdAndMerchantId(String merchantTrxnRefId, long merchantId);

}