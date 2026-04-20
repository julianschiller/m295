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
 * Entity class representing the address database table
 * @author Julian Schiller
 */
@Entity
@Table(name="address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    /**
     * Streetname and housenumber
     */
    @Column(nullable=false, length=40)
    @Schema(description="Addres with streetname and housenumber", example="Hoher Weg 23")
    private String address;
    @Column(nullable=false, length=30)
    @Schema(description="City of the address", example="Allschwil")
    private String city;
    @Column(nullable=false, length=8)
    @Schema(description="zip code of the city", example="4123")
    private String zip;
}
