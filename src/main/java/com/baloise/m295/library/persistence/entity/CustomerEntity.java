package com.baloise.m295.library.persistence.entity;

import java.util.Date;

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
    private long id;
    @Column(nullable=false, length=40)
    private String firstname;
    @Column(nullable=false, length=40)
    private String lastname;
    @Column(nullable=false, columnDefinition="date")
    private Date birthdate;
    @Column(nullable=false, length=50)
    private String email;
    @ManyToOne
    @JoinColumn(name="addressid", nullable=false)
    private AddressEntity address;
}
