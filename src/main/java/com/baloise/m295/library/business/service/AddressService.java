package com.baloise.m295.library.business.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.AddressEntity;
import com.baloise.m295.library.common.CustomerEntity;
import com.baloise.m295.library.persistence.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for handling address business logic
 * Responsible for retrieving, creating and deleting addresses
 * Also ensures that addresses cannot be deleted if they are still referenced by customers
 *
 * @author Julian Schiller
 */
@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository repo;
    private final CustomerService customerService;

    /**
     * Retrieves addresses based on optional filters
     * If address is provided, filters by street + house number
     * If zip is provided, filters by postal code
     * If no filter is provided, returns all addresses
     *
     * @param address optional street + house number filter
     * @param zip optional postal code filter
     * @return list of matching addresses
     */
    public List<AddressEntity> getAddresses(String address, String zip) {

        List<AddressEntity> entities;

        if (address != null) {
            entities = repo.findByAddress(address);
        } else if (zip != null) {
            entities = repo.findByZip(zip);
        } else {
            entities = repo.findAll();
        }

        return entities;
    }

    /**
     * Creates a new address in the database
     *
     * @param address address entity to persist
     * @return the created AddressEntity
     */
    public AddressEntity createAddress(AddressEntity address) {
        return repo.save(address);
    }

    /**
     * Deletes an address by ID
     * Deletion is only allowed if no customer references this address
     *
     * @param id address ID
     * @throws ResponseStatusException if address is not found
     * @throws ResponseStatusException if address is still referenced by customers
     */
    public void deleteAddress(long id) {

        AddressEntity address = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));

        List<CustomerEntity> customerWithAddress = customerService.getCustomersWithFilter(null, id);

        if (!customerWithAddress.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Address is still referenced by customers");
        }

        repo.delete(address);
    }
}
