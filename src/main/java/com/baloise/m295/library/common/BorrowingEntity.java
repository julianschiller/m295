package com.baloise.m295.library.common;

import java.util.Date;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing the borrowing database table
 * @author Julian Schiller
 */
@Entity
@Table(name="borrowing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, columnDefinition="date")
    @Schema(description="Date of the start of the borrowing", example="2026-04-15")
    private LocalDate borrowdate;
    /**
     * duration of the borrowing in days
     */
    @Column(nullable=false)
    @Schema(description="Duration of the borrowing in days", example="20")
    private Short duration;
    @ManyToOne
    @JoinColumn(name="customerid", nullable=false)
    @Schema(description="The customer that borrows the media")
    private CustomerEntity customer;
    @ManyToOne()
    @JoinColumn(name="mediaid", nullable=false)
    @Schema(description="The media that gets borrowed")
    private MediaEntity media;

    /**
     * Sets default values before persisting:
     * borrowdate = current date, duration = 14 days (if null)
     */
    @PrePersist
    public void prePersist() {
        if (borrowdate == null) {
            borrowdate = LocalDate.now();
        }
        if (duration == null) {
            duration = 14;
        }
    }
}
