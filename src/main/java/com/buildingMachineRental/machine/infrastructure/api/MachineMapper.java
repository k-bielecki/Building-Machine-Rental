package com.buildingMachineRental.machine.infrastructure.api;

import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineDetails;

public class MachineMapper {

    static Machine mapMachineFromDto(MachineDto machineDto, Long id){
        return Machine.builder()
                .id(id)
                .name(machineDto.getName())
                .pricePerDay(machineDto.getPricePerDay())
                .rented(machineDto.isRented())
                .condition(machineDto.getCondition())
                .details(mapMachineDetails(machineDto.getDetailsDto()))
                .build();
    }

    public static MachineDto mapMachineToDto(Machine machine){
        return MachineDto.builder()
                .name(machine.getName())
                .pricePerDay(machine.getPricePerDay())
                .rented(machine.getRented())
                .condition(machine.getCondition())
                .detailsDto(mapMachineDetailsDto(machine.getDetails()))
                .build();
    }

    private static MachineDetailsDto mapMachineDetailsDto(MachineDetails machineDetails) {
        return MachineDetailsDto.builder()
                .machineHour(machineDetails.getMachineHour())
                .weight(machineDetails.getWeight())
                .horsePower(machineDetails.getHorsePower())
                .category(machineDetails.getCategory())
                .build();
    }

    private static MachineDetails mapMachineDetails(MachineDetailsDto machineDetailsDto){
        return MachineDetails.builder()
                .machineHour(machineDetailsDto.getMachineHour())
                .weight(machineDetailsDto.getWeight())
                .horsePower(machineDetailsDto.getHorsePower())
                .category(machineDetailsDto.getCategory())
                .build();
    }
}
