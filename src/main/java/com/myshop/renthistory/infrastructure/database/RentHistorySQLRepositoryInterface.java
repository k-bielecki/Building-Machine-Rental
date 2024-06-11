package com.myshop.renthistory.infrastructure.database;

import com.myshop.renthistory.domain.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RentHistorySQLRepositoryInterface extends JpaRepository<RentHistory, Long> {

    List<RentHistory> findAllByUserId(Long id);
}
