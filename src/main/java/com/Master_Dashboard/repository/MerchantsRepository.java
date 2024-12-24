package com.Master_Dashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Master_Dashboard.entity.Merchants;

@Repository
public interface MerchantsRepository extends JpaRepository<Merchants, Long> {

	public boolean existsByMerchantEmail(String encString) ;

	public boolean existsByMerchantPhone(String encString);

	public boolean existsByMerchantPassword(String encString) ;

	public Optional<Merchants> findByMerchantEmail(String encString);

	
	
}
