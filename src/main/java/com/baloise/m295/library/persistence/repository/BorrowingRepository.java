package com.baloise.m295.library.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baloise.m295.library.common.BorrowingEntity;

/**
 * Repository for accessing BorrwingEntity data
 * @author Julian Schiller
 */
public interface BorrowingRepository extends JpaRepository<BorrowingEntity, Long>{
    Optional<BorrowingEntity> findByMedia_Id(int mediaId);
}
