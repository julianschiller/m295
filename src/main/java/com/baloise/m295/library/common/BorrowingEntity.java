package com.baloise.m295.library.common;

import java.util.Date;

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
    private long id;
    @Column(nullable=false, columnDefinition="date")
    private Date borrowdate;
    /**
     * duration of the borrowing in days
     */
    @Column(nullable=false)
    private Short duration;
    @ManyToOne
    @JoinColumn(name="customerid", nullable=false)
    private CustomerEntity customer;
    @ManyToOne()
    @JoinColumn(name="mediaid", nullable=false)
    private MediaEntity media;

    /**
     * Sets default values before persisting:
     * borrowdate = current date, duration = 14 days (if null)
     */
    @PrePersist
    public void prePersist() {
        if (borrowdate == null) {
            borrowdate = new Date();
        }
        if (duration == null) {
            duration = 14;
        }
    }
}
