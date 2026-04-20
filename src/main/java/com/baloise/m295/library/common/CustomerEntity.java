package com.baloise.m295.library.common;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing the customer database table
 * @author Julian Schiller
 */
@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=40)
    @Schema(description="Firstname of the customer", example="Peter")
    private String firstname;
    @Column(nullable=false, length=40)
    @Schema(description="Lastname of the customer", example="Mustermann")
    private String lastname;
    @Column(nullable=false, columnDefinition="date")
    @Schema(description="Birthdate of the customer", example="2010-10-23")
    private Date birthdate;
    @Column(nullable=false, length=50)
    @Schema(description="Email of the customer", example="peter.mustermann@gmail.com")
    private String email;
    @ManyToOne()
    @JoinColumn(name="addressid", nullable=false)
    @Schema(description="Address of the customer")
    private AddressEntity address;
}
