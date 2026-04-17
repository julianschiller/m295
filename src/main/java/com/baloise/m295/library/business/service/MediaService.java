package com.baloise.m295.library.business.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.MediaEntity;
import com.baloise.m295.library.persistence.repository.MediaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository repo;

    public List<MediaEntity> getAllMedia(){
        return repo.findAll();
    }

    public List<MediaEntity> getAllFilteredByTitle(String title) {
        return repo.findByTitle(title);
    }

    public MediaEntity getMediaById(long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found"));
    }

    public void createNewMedia(MediaEntity media) {
        repo.save(media);
    }

    public void editMedia(long id, MediaEntity updatedMedia) {
        MediaEntity oldMedia = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found"));

        oldMedia = mergeMedia(oldMedia, updatedMedia);
        repo.save(oldMedia);
    }

    public void deleteMedia(long id) {
        MediaEntity media = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found"));

        repo.delete(media);
    }

    private MediaEntity mergeMedia(MediaEntity oldEntity, MediaEntity newEntity) {
        if (newEntity.getGenre() != null) {
            oldEntity.setGenre(newEntity.getGenre());
        }

        if (newEntity.getMinage() != null) {
            oldEntity.setMinage(newEntity.getMinage());
        }

        if (newEntity.getIsbn() != null) {
            oldEntity.setIsbn(newEntity.getIsbn());
        }

        if (newEntity.getLocationcode() != null) {
            oldEntity.setLocationcode(newEntity.getLocationcode());
        }

        return oldEntity;
    }
}
