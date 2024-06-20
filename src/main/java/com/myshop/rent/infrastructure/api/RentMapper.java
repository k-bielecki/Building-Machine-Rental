package com.myshop.rent.infrastructure.api;

import com.myshop.rent.domain.Rent;

public class RentMapper {

    public static RentDto mapRentToDto(Rent rent) {
        return new RentDto(rent.getUserId(), rent.getMachineId());
    }
}
