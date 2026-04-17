package com.baloise.m295.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.CustomerService;
import com.baloise.m295.library.common.CustomerEntity;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * REST controller for customer endpoints
 * Handles HTTP requests for customer CRUD operations
 *
 * @author Julian Schiller
 */
@RestController
@RequestMapping("/library/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    /**
     * Retrieves a customer by ID
     *
     * @param id customer ID
     * @return customer entity
     */
    @GetMapping("/{id}") 
    public CustomerEntity getCustomerById(@PathVariable long id) {
        return service.getCustomer(id);
    }

    /**
     * Retrieves customers filtered by name or address
     *
     * @param name optional lastname filter
     * @param addressId optional address ID filter
     * @return list of matching customers
     */
    @GetMapping
    public List<CustomerEntity> getCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long addressId) {
        return service.getCustomersWithFilter(name, addressId);
    }

    /**
     * Creates a new customer
     *
     * @param customer customer data to create
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerEntity customer){
        service.createNewCustomer(customer);
    }

    /**
     * Updates an existing customer
     *
     * @param customer updated customer data
     * @param id customer ID
     */
    @PatchMapping("/{id}")
    public void editCustomer(@RequestBody CustomerEntity customer, @PathVariable long id) {
        service.editCustomer(id, customer);
    }

    /**
     * Deletes a customer
     *
     * @param id customer ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable long id) {
        service.deleteCustomer(id);
    }
}
