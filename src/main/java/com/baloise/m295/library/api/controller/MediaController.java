package com.baloise.m295.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.MediaService;
import com.baloise.m295.library.common.MediaEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
 * REST controller for media endpoints
 * Handles HTTP requests for media CRUD operations
 *
 * @author Julian Schiller
 */
@RestController
@RequestMapping("/library/media")
@RequiredArgsConstructor
@Tag(name="Medias", description="CRUD-Operations for the medias")
public class MediaController {

    private final MediaService service;

    /**
     * Retrieves all media or filters by title if provided
     *
     * @param title optional title filter
     * @return list of media entries
     */
    @GetMapping
    @Operation(summary="Get all medias, can optionally be filtered with a title")
    @ApiResponse(responseCode="200", description="List of the found medias")
    public List<MediaEntity> getMedia(@RequestParam(required = false) String title) {
        if (title != null) {
            return service.getAllFilteredByTitle(title);
        } else {
            return service.getAllMedia();
        }
    }

    /**
     * Retrieves a media entry by ID
     *
     * @param id media ID
     * @return media entity
     */
    @GetMapping("/{id}")
    @Operation(summary="Get a media by its id")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="The found media"),
        @ApiResponse(responseCode="404", description="No media with that id was found")
    })
    public MediaEntity getMediaById(@PathVariable long id) {
        return service.getMediaById(id);
    }

    /**
     * Creates a new media entry
     *
     * @param media media data to create
     * @return the created MediaEntity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Create a new media")
    @ApiResponse(responseCode="201", description="Media was successfully created")
    public MediaEntity createMedia(@RequestBody MediaEntity media) {
        return service.createNewMedia(media);
    }

    /**
     * Updates an existing media entry
     *
     * @param media updated media data
     * @param id media ID
     * @return the edited media
     */
    @PatchMapping("/{id}")
    @Operation(summary="Edit a media")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Media was edited successfully"),
        @ApiResponse(responseCode="404", description="No media found with that id")
    })
    public MediaEntity editMedia(@RequestBody MediaEntity media, @PathVariable long id) {
        return service.editMedia(id, media);
    }

    /**
     * Deletes a media entry
     *
     * @param id media ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary="Delete a media")
    @ApiResponses({
        @ApiResponse(responseCode="204", description="Media deleted"),
        @ApiResponse(responseCode="404", description="No media found with that id")
    })
    public void deleteMedia(@PathVariable long id) {
        service.deleteMedia(id);
    }
}
