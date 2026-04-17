package com.baloise.m295.library.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baloise.m295.library.persistence.entity.AddressEntity;
import com.baloise.m295.library.persistence.entity.CustomerEntity;

/**
 * Repository for accessing CustomerEntity data
 * @author Julian Schiller
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

    /**
     * @param lastname lastname of the customer
     * @return List of matching customers
     */
    List<CustomerEntity> findByLastname(String lastname);
    
    /**
     * @param address address of the customer
     * @return list of matching customers
     */
    List<CustomerEntity> findByAddress(AddressEntity address);
}
