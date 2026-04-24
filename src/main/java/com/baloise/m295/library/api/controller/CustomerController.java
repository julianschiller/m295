package com.baloise.m295.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.CustomerService;
import com.baloise.m295.library.common.CustomerEntity;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("/library/customers")
@RequiredArgsConstructor
@Tag(name="Customers", description="CRUD-Operations for the customers")
public class CustomerController {

    private final CustomerService service;

    /**
     * Retrieves a customer by ID
     *
     * @param id customer ID
     * @return customer entity
     */
    @GetMapping("/{id}")
    @Operation(summary="Get a Customer by his ID")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="The customer with the ID"),
        @ApiResponse(responseCode="404", description="No customer with that ID found")
    })
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
    @Operation(summary="Get Customers by name or addressId")
    @ApiResponse(responseCode="200", description="List of all Customers with that name or addressId")
    public List<CustomerEntity> getCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long addressId) {
        return service.getCustomersWithFilter(name, addressId);
    }

    /**
     * Creates a new customer
     *
     * @param customer customer data to create
     * @return the created CustomerEntity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Create a new customer")
    @ApiResponses({
        @ApiResponse(responseCode="201", description="New customer was created"),
        @ApiResponse(responseCode="400", description="No address was sent"),
        @ApiResponse(responseCode="404", description="No address found with that id")
    })
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer){
        return service.createNewCustomer(customer);
    }

    /**
     * Updates an existing customer
     *
     * @param customer updated customer data
     * @param id customer ID
     * @return the edited CustomerEntity
     */
    @PatchMapping("/{id}")
    @Operation(summary="Edit a customer")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Customer successfully edited"),
        @ApiResponse(responseCode="404", description="Customer with that id not found")
    })
    public CustomerEntity editCustomer(@RequestBody CustomerEntity customer, @PathVariable long id) {
        return service.editCustomer(id, customer);
    }

    /**
     * Deletes a customer
     *
     * @param id customer ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary="Delete a customer")
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Customer successfully deleted"),
        @ApiResponse(responseCode="404", description="Customer with that id wasn't found")
    })
    public void deleteCustomer(@PathVariable long id) {
        service.deleteCustomer(id);
    }
}
