package com.myshop.renthistory.domain;

import java.util.List;

public class RentHistoryFacade {

    private final RentHistoryService rentHistoryService;

    RentHistoryFacade(RentHistoryService rentHistoryService) {
        this.rentHistoryService = rentHistoryService;
    }

    List<RentHistory> getAllRentHistory(){
        return rentHistoryService.getAllRentHistory();
    }

    List<RentHistory> getRentHistoryByUser(Long userId){
        return rentHistoryService.getRentHistoryByUser(userId);
    }

    RentHistory addRentHistory(RentHistory rentHistory){
        return rentHistoryService.addRentHistory(rentHistory);
    }
}
