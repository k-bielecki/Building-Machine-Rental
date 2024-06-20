package com.buildingMachineRental.machine.infrastructure.database;

import com.buildingMachineRental.machine.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachinePostgreSQLRepositoryInterface extends JpaRepository<Machine, Long> {

    @Query(value = "SELECT * FROM machine ORDER BY name ASC limit ?2 offset ?1", nativeQuery = true)
    List<Machine> findByNameAscendingWithPagination(Long offset, Long limit);

    @Query(value = "SELECT * FROM machine ORDER BY name DESC limit ?2 offset ?1", nativeQuery = true)
    List<Machine> findByNameDescendingWithPagination(Long offset, Long limit);

    @Query(value = "SELECT * FROM machine ORDER BY price_per_day ASC limit ?2 offset ?1", nativeQuery = true)
    List<Machine> findByPriceAscendingWithPagination(Long offset, Long limit);

    @Query(value = "SELECT * FROM machine ORDER BY price_per_day DESC limit ?2 offset ?1", nativeQuery = true)
    List<Machine> findByPriceDescendingWithPagination(Long offset, Long limit);
}
