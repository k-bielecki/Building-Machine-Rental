package com.myshop.renthistory.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentHistoryId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "machine_id")
    private Long machineId;

    @Column(name = "rented_date")
    private LocalDateTime rentedDate;

    @Column(name = "returned_date")
    private LocalDateTime returnedDate;
}
