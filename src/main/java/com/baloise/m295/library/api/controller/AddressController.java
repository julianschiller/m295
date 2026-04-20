package com.baloise.m295.library.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.AddressService;
import com.baloise.m295.library.common.AddressEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for address endpoints
 * Handles HTTP requests for address operations
 *
 * @author Julian Schiller
 */
@RestController
@RequestMapping("/library/addresses")
@RequiredArgsConstructor
@Tag(name="Addresses", description="CRUD-Operation for the addresses")
public class AddressController {

    private final AddressService service;

    /**
     * Retrieves addresses filtered by address or zip
     *
     * @param address optional street + house number filter
     * @param zip optional postal code filter
     * @return list of matching addresses
     */
    @GetMapping
    @Operation(summary="Get all addresses")
    @ApiResponse(responseCode="200", description="List of all addresses")
    public List<AddressEntity> getAddresses(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String zip) {
        return service.getAddresses(address, zip);
    }

    /**
     * Creates a new address
     *
     * @param address address data to create
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Create new address")
    @ApiResponse(responseCode="201", description="Address created")
    public void createAddress(@RequestBody AddressEntity address) {
        service.createAddress(address);
    }

    /**
     * Deletes an address
     *
     * @param id address ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary="delete address")
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Address deleted succesfully"),
        @ApiResponse(responseCode="400", description="User is still referencing the address"),
        @ApiResponse(responseCode="404", description="No address with this id found")
    })
    public void deleteAddress(@PathVariable long id) {
        service.deleteAddress(id);
    }
}
