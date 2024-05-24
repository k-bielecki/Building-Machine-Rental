package com.myshop.machine.infrastructure.api;

import com.myshop.machine.domain.MachineCategory;
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
