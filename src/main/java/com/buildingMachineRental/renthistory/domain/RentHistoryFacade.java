package com.buildingMachineRental.renthistory.domain;

import java.util.List;

public class RentHistoryFacade {

    private final RentHistoryService rentHistoryService;

    RentHistoryFacade(RentHistoryService rentHistoryService) {
        this.rentHistoryService = rentHistoryService;
    }

    public List<RentHistory> getAllRentHistory(){
        return rentHistoryService.getAllRentHistory();
    }

    public List<RentHistory> getRentHistoryByUser(Long userId){
        return rentHistoryService.getRentHistoryByUser(userId);
    }

    public RentHistory addRentHistory(RentHistory rentHistory){
        return rentHistoryService.addRentHistory(rentHistory);
    }
}
