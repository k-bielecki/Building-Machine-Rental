package com.buildingMachineRental.renthistory.infrastructure.database;

import com.buildingMachineRental.renthistory.domain.RentHistory;
import com.buildingMachineRental.renthistory.domain.RentHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentHistorySQLRepository implements RentHistoryRepository {

    private final RentHistorySQLRepositoryInterface rentHistorySQLRepositoryInterface;

    public RentHistorySQLRepository(RentHistorySQLRepositoryInterface rentHistorySQLRepositoryInterface) {
        this.rentHistorySQLRepositoryInterface = rentHistorySQLRepositoryInterface;
    }

    @Override
    public List<RentHistory> getAllRentHistory() {
        return rentHistorySQLRepositoryInterface.findAll();
    }

    @Override
    public List<RentHistory> getRentHistoryByUser(Long userId) {
        return rentHistorySQLRepositoryInterface.findAllByUserId(userId);
    }

    @Override
    public RentHistory addRentHistory(RentHistory rentHistory) {
        return rentHistorySQLRepositoryInterface.save(rentHistory);
    }
}
