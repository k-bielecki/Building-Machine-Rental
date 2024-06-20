package com.buildingMachineRental.machine.infrastructure.api;

import com.buildingMachineRental.machine.domain.MachineCategory;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MachineDetailsDto {
    private int machineHour;
    private BigDecimal weight;
    private int horsePower;
    private MachineCategory category;
}
