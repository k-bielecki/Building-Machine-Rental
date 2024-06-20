package com.buildingMachineRental.renthistory.infrastructure.database;

import com.buildingMachineRental.renthistory.domain.RentHistory;
import com.buildingMachineRental.renthistory.domain.RentHistoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentHistoryRepositoryInMemory implements RentHistoryRepository {

    private final Map<Long, RentHistory> rentHistoryRepositoryMap = new HashMap<>();

    @Override
    public List<RentHistory> getAllRentHistory() {
        return rentHistoryRepositoryMap.values().stream().toList();
    }

    @Override
    public List<RentHistory> getRentHistoryByUser(Long userId) {
        return rentHistoryRepositoryMap.values().stream()
                .filter(rentHistory -> rentHistory.getUserId().equals(userId))
                .toList();
    }

    @Override
    public RentHistory addRentHistory(RentHistory rentHistory) {
        return rentHistoryRepositoryMap.put(rentHistory.getRentHistoryId(), rentHistory);
    }
}
