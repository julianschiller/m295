package com.baloise.m295.library.common;

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
 * @author Julian Schiller
 */
@Entity
@Table(name="media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false, length=50)
    private String title;
    @Column(nullable=false, length=80)
    private String author;
    @Column(length=20)
    private String genre;
    private Short minage;
    @Column(length=20)
    private String isbn;
    @Column(length=20)
    private String locationcode;
}
