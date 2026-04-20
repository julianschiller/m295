package com.baloise.m295.library.common;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing the media database table
 * 
 * @author Julian Schiller
 */
@Entity
@Table(name = "media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=50)
    @Schema(description="Title of the media", example="Harry Potter")
    private String title;
    @Column(nullable=false, length=80)
    @Schema(description="Author of the media", example="J.K. Rowling")
    private String author;
    @Column(length=20)
    @Schema(description="Genre of the media", example="Fantasy")
    private String genre;
    @Schema(description="Minimum age requirement in years", example="12")
    private Short minage;
    @Column(length=20)
    @Schema(description="ISBN number of the media", example="978-3-7549-5401-0")
    private String isbn;
    @Column(length=20)
    @Schema(description="Code of where the media can be found in the library", example="A01-09-E3")
    private String locationcode;
}
