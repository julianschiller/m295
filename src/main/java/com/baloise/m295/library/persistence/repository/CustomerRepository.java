package com.baloise.m295.library.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baloise.m295.library.common.AddressEntity;
import com.baloise.m295.library.common.CustomerEntity;

/**
 * Repository for accessing CustomerEntity data
 * @author Julian Schiller
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

    /**
     * find all customers with specific lastname
     * 
     * @param lastname lastname of the customer
     * @return List of matching customers
     */
    List<CustomerEntity> findByLastname(String lastname);
    
    /**
     * find all customers with a specific address (uses the entity)
     * 
     * @param address address of the customer
     * @return list of matching customers
     */
    List<CustomerEntity> findByAddress(AddressEntity address);

    /**
     * finds the customer on a specific address (uses the id of the entity)
     * 
     * @param addressId id of the address
     * @return all customers with that address
     */
    List<CustomerEntity> findByAddress_Id(Long addressId);
}
