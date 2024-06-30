package com.buildingMachineRental.rent.infrastructure.database;

import com.buildingMachineRental.rent.domain.Rent;
import com.buildingMachineRental.rent.domain.RentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RentPostgreSQLRepository implements RentRepository {

    private final RentPostgreSQLRepositoryInterface postgreSQLRepositoryDatabase;

    public RentPostgreSQLRepository(RentPostgreSQLRepositoryInterface postgreSQLRepositoryDatabase) {
        this.postgreSQLRepositoryDatabase = postgreSQLRepositoryDatabase;
    }

    @Override
    public Rent getRent(Long machineId) {
        return postgreSQLRepositoryDatabase.findByMachineId(machineId);
    }

    @Override
    public List<Rent> getAllRents() {
        return postgreSQLRepositoryDatabase.findAll();
    }

    @Override
    public List<Rent> getAllRentsByUserId(Long userId) {
        return postgreSQLRepositoryDatabase.findAllByUserId(userId);
    }

    @Override
    public List<Long> getAllRentedMachinesIds() {
        return postgreSQLRepositoryDatabase.findAll().stream()
                .map(Rent::getMachineId)
                .toList();
    }

    @Override
    public List<Long> getAllRentedMachinesIdsByUser(Long userId) {
        return postgreSQLRepositoryDatabase.findAll().stream()
                .filter(rent -> rent.getUserId().equals(userId))
                .map(Rent::getMachineId)
                .toList();
    }

    @Override
    @Transactional
    public Rent addRent(Rent rent) {
        return postgreSQLRepositoryDatabase.save(rent);
    }

    @Override
    @Transactional
    public void deleteRent(Long machineId) {
        postgreSQLRepositoryDatabase.deleteByMachineId(machineId);
    }
}
