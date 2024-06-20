package com.myshop.rent.infrastructure.database;

import com.myshop.rent.domain.Rent;
import com.myshop.rent.domain.RentRepository;
import org.springframework.stereotype.Repository;

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
                .map(rent -> rent.getMachineId())
                .toList();
    }

    @Override
    public List<Long> getAllRentedMachinesIdsByUser(Long userId) {
        return postgreSQLRepositoryDatabase.findAll().stream()
                .filter(rent -> rent.getUserId().equals(userId))
                .map(rent -> rent.getMachineId())
                .toList();
    }

    @Override
    public Rent addRent(Rent rent) {
        return postgreSQLRepositoryDatabase.save(rent);
    }

    @Override
    public void deleteRent(Long machineId) {
        postgreSQLRepositoryDatabase.deleteByMachineId(machineId);
    }
}
