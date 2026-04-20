package com.baloise.m295.library.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.AddressEntity;
import com.baloise.m295.library.common.CustomerEntity;
import com.baloise.m295.library.persistence.repository.AddressRepository;
import com.baloise.m295.library.persistence.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for handling customer business logic.
 * Responsible for retrieving, creating, updating and deleting customers.
 *
 * @author Julian Schiller
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;
    private final AddressRepository addressRepo;

    /**
     * Retrieves a customer by its ID
     *
     * @param id customer ID
     * @return found customer entity
     * @throws ResponseStatusException if customer is not found
     */
    public CustomerEntity getCustomer(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    /**
     * Retrieves customers based on optional filters
     * If name is provided, filters by lastname
     * If addressId is provided, filters by address
     * If no filter is provided, returns an empty list
     *
     * @param name      optional lastname filter
     * @param addressId optional address ID filter
     * @return list of matching customers
     */
    public List<CustomerEntity> getCustomersWithFilter(String name, Long addressId) {

        List<CustomerEntity> customers;

        if (name != null) {
            customers = repo.findByLastname(name);
        } else if (addressId != null) {
            return repo.findByAddress_Id(addressId);
        } else {
            customers = new ArrayList<>();
        }

        return customers;
    }

    /**
     * Creates a new customer in the database
     *
     * @param customer customer entity to persist
     * @throws ResponseStatusException when an id of adress is sent but the address
     *                                 doesnt exist
     * @return the created CustomerEntity
     */
    public CustomerEntity createNewCustomer(CustomerEntity customer) {
        AddressEntity address = customer.getAddress();

        if (address == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address is required");
        }

        Long addressId = address.getId();

        if (addressId != null) {
            address = addressRepo.findById(addressId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
        } else {
            address = addressRepo.save(address);
        }

        customer.setAddress(address);
        return repo.save(customer);
    }

    /**
     * Updates an existing customer
     * Only non-null fields from the request are applied
     *
     * @param id              customer ID
     * @param updatedCustomer incoming updated data
     * @throws ResponseStatusException if customer is not found
     * @return the edited CustomerEntity
     */
    public CustomerEntity editCustomer(long id, CustomerEntity updatedCustomer) {
        CustomerEntity oldCustomer = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        oldCustomer = mergeCustomers(updatedCustomer, oldCustomer);

        return repo.save(oldCustomer);
    }

    /**
     * Merges updated customer data into existing entity
     * Only non-null fields are overwritten
     *
     * @param newEntity incoming customer data
     * @param oldEntity existing database entity
     * @return merged customer entity
     */
    private CustomerEntity mergeCustomers(CustomerEntity newEntity, CustomerEntity oldEntity) {

        if (newEntity.getEmail() != null) {
            oldEntity.setEmail(newEntity.getEmail());
        }

        if (newEntity.getAddress() != null) {
            oldEntity.setAddress(newEntity.getAddress());
        }

        return oldEntity;
    }

    /**
     * Deletes a customer by ID
     *
     * @param id customer ID
     * @throws ResponseStatusException if customer is not found
     */
    public void deleteCustomer(long id) {
        CustomerEntity customer = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        repo.delete(customer);
    }
}