package com.baloise.m295.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.MediaService;
import com.baloise.m295.library.common.MediaEntity;

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


@RestController
@RequestMapping("/library/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService service;

    @GetMapping
    public List<MediaEntity> getMedia(@RequestParam(required=false) String title) {
        if (title != null) {
            return service.getAllFilteredByTitle(title);
        } else {
            return service.getAllMedia();
        }
    }

    @GetMapping("/{id}")
    public MediaEntity getMediaById(@PathVariable long id) {
        return service.getMediaById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMedia(@RequestBody MediaEntity media) {
        service.createNewMedia(media);
    }

    @PatchMapping("/{id}")
    public void editMedia(@RequestBody MediaEntity media, @PathVariable long id) {
        service.editMedia(id, media);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedia(@PathVariable long id) {
        service.deleteMedia(id);
    }
}
