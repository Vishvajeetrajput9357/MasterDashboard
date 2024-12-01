package com.Master_Dashboard.repository;


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
            "WHERE p.transaction_date BETWEEN :startDate AND :endDate " +
            "  AND (COALESCE(:statusId, '') = '' OR p.transaction_status_id = COALESCE(NULLIF(:statusId, ''), p.transaction_status_id)) " +
            "  AND (COALESCE(:merchantId, '') = '' OR p.merchant_id = COALESCE(NULLIF(:merchantId, ''), p.merchant_id)) " +
            "  AND (COALESCE(:serviceName, '') = '' OR p.service_name = COALESCE(NULLIF(:serviceName, ''), p.service_name))", 
    nativeQuery = true)
	Page<ENachTransactionDetails> findByENachTransactionRequest(
	    @Param("startDate") String startDate,
	    @Param("endDate") String endDate,
	    @Param("serviceName") String serviceName,
	    @Param("statusId") Long statusId,
	    @Param("merchantId") Long merchantId,
	    Pageable pageable 
	);
}
