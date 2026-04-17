package com.baloise.m295.library.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baloise.m295.library.persistence.entity.BorrowingEntity;

/**
 * Repository for accessing BorrwingEntity data
 * @author Julian Schiller
 */
public interface BorrowingRepository extends JpaRepository<BorrowingEntity, Long>{

}
