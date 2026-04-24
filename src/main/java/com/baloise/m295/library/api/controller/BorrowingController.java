package com.baloise.m295.library.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.BorrowingService;
import com.baloise.m295.library.common.BorrowingEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for borrowing endpoints
 * Handles HTTP requests for borrowing operations
 *
 * @author Julian Schiller
 */
@RestController
@CrossOrigin
@RequestMapping("/library/borrowings")
@RequiredArgsConstructor
@Tag(name="Borrowings", description="CRUD-Operations for the borrowings")
public class BorrowingController {

    private final BorrowingService service;

    /**
     * Retrieves all borrowings
     *
     * @return list of all borrowings
     */
    @GetMapping
    @Operation(summary="Get all borrowings")
    @ApiResponse(responseCode="200", description="List of all active borrowings")
    public List<BorrowingEntity> getAllBorrowings() {
        return service.getAllBorrowings();
    }

    /**
     * Creates a new borrowing
     *
     * @param borrowing borrowing data to create
     * @return the saved BorrowingEntity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Create a new borrowing")
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Successfully created a new borrowing"),
        @ApiResponse(responseCode="404", description="The customer or the media that is used in the borrowing doesn't exist"),
        @ApiResponse(responseCode="409", description="The media is already borrowed")
    })
    public BorrowingEntity createBorrowing(@RequestBody BorrowingEntity borrowing) {
        return service.createBorrowing(borrowing);
    }

    /**
     * Extends an existing borrowing
     *
     * @param borrowing borrowing data containing new duration
     * @param id borrowing ID
     * @return the edited Entity
     */
    @PatchMapping("/{id}")
    @Operation(summary="Extend a borrowing by setting a new duration")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="New Duration was successfully set"),
        @ApiResponse(responseCode="404", description="The borrowing doesn't exist")
    })
    public BorrowingEntity extendBorrowing(@RequestBody BorrowingEntity borrowing, @PathVariable long id) {
        return service.extendBorrowing(id, borrowing.getDuration());
    }

    /**
     * Ends a borrowing by media ID
     *
     * @param mediaId media ID of the borrowing to delete
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary="End a borrowing")
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Borrowing deleted"),
        @ApiResponse(responseCode="404", description="Borrowing not found")
    })
    public void deleteBorrowing(@RequestParam int mediaId) {
        service.endBorrowing(mediaId);
    }
}