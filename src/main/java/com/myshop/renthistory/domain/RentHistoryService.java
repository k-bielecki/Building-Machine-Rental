package com.myshop.renthistory.domain;

import java.util.List;

class RentHistoryService {

    private final RentHistoryRepository rentHistoryRepository;

    RentHistoryService(RentHistoryRepository rentHistoryRepository) {
        this.rentHistoryRepository = rentHistoryRepository;
    }

    List<RentHistory> getAllRentHistory(){
        return rentHistoryRepository.getAllRentHistory();
    }

    List<RentHistory> getRentHistoryByUser(Long userId){
        return rentHistoryRepository.getRentHistoryByUser(userId);
    }

    RentHistory addRentHistory(RentHistory rentHistory){
        return rentHistoryRepository.addRentHistory(rentHistory);
    }
}
