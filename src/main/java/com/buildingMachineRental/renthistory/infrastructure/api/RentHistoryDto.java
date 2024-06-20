package com.buildingMachineRental.renthistory.infrastructure.api;

import java.time.LocalDateTime;

public record RentHistoryDto(
        Long userId,
        Long machineId,
        LocalDateTime rentedDate,
        LocalDateTime returnedDate
) {
}
