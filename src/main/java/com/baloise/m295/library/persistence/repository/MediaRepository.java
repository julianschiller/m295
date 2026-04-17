package com.baloise.m295.library.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baloise.m295.library.persistence.entity.MediaEntity;

/**
 * Repository for accessing MediaEntity data
 * @author Julian Schiller
 */
public interface MediaRepository extends JpaRepository<MediaEntity, Long>{

    /**
     * @param title title of the Media
     * @return List of matching medias
     */
    List<MediaEntity> findByTitle(String title);
}
