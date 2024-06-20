package com.buildingMachineRental.rent.infrastructure.api;

import com.buildingMachineRental.rent.domain.Rent;

public class RentMapper {

    public static RentDto mapRentToDto(Rent rent) {
        return new RentDto(rent.getUserId(), rent.getMachineId());
    }
}
