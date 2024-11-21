package com.Master_Dashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Master_Dashboard.entity.MerchantInfo;

@Repository
public interface MerchantInfoRepository extends JpaRepository<MerchantInfo, Long> {

	Optional<MerchantInfo> findByClientSecret(String encString);
	
}
