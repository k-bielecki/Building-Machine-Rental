package com.myshop.renthistory.domain;

import java.util.List;

public interface RentHistoryRepository {

    List<RentHistory> getAllRentHistory();

    List<RentHistory> getRentHistoryByUser(Long userId);

    RentHistory addRentHistory(RentHistory rentHistory);
}
