package com.buildingMachineRental.renthistory.infrastructure.database;

import com.buildingMachineRental.renthistory.domain.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RentHistorySQLRepositoryInterface extends JpaRepository<RentHistory, Long> {

    List<RentHistory> findAllByUserId(Long userId);
}
