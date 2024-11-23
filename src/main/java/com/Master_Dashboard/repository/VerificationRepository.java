package com.Master_Dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Master_Dashboard.entity.VerificationEntity;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationEntity, Long> {

}
