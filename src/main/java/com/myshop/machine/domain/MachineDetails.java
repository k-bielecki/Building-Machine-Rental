package com.myshop.machine.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;
    private int machineHour;
    private BigDecimal weight;
    private int horsePower;
    private MachineCategory category;
}
