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
    private long id;
    /**
     * Streetname and housenumber
     */
    @Column(nullable=false, length=40)
    private String address;
    @Column(nullable=false, length=30)
    private String city;
    @Column(nullable=false, length=8)
    private String zip;
}
