package com.buildingMachineRental.rent.infrastructure.database;

import com.buildingMachineRental.rent.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentPostgreSQLRepositoryInterface extends JpaRepository<Rent, Long> {

    Rent findByMachineId(Long machineId);

    List<Rent> findAllByUserId(Long userId);

    void deleteByMachineId(Long machineId);
}
