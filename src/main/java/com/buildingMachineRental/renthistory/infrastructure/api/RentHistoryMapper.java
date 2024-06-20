package com.buildingMachineRental.renthistory.infrastructure.api;

import com.buildingMachineRental.renthistory.domain.RentHistory;

public class RentHistoryMapper {

    static RentHistory mapRentHistoryFromDto(RentHistoryDto rentHistoryDto) {
        return RentHistory.builder()
                .userId(rentHistoryDto.userId())
                .machineId(rentHistoryDto.machineId())
                .rentedDate(rentHistoryDto.rentedDate())
                .returnedDate(rentHistoryDto.returnedDate())
                .build();
    }

    public static RentHistoryDto mapRentHistoryToDto(RentHistory rentHistory){
        return new RentHistoryDto(
                rentHistory.getUserId(),
                rentHistory.getMachineId(),
                rentHistory.getRentedDate(),
                rentHistory.getReturnedDate()
        );
    }
}
