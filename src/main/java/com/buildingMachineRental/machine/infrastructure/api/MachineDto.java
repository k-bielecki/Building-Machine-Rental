package com.buildingMachineRental.machine.infrastructure.api;

import com.buildingMachineRental.machine.domain.MachineCondition;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MachineDto {
    private String name;
    private BigDecimal pricePerDay;
    private boolean rented;
    private MachineCondition condition;
    private MachineDetailsDto detailsDto;
}
