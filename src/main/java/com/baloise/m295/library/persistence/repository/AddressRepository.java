package com.baloise.m295.library.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baloise.m295.library.persistence.entity.AddressEntity;

/**
 * Repository for accessing AddressEntity data
 * @author Julian Schiller
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    /**
     * Finds an address by its street name and house number
     *
     * @param address street name and house number
     * @return list of matching addresses
     */
    List<AddressEntity> findByAddress(String address);

    /**
     * Finds all addresses with the given zip code
     *
     * @param zip postal code
     * @return list of matching addresses
     */
    List<AddressEntity> findByZip(String zip);
}