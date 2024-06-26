package com.buildingMachineRental.rent.infrastructure.database;

import com.buildingMachineRental.rent.domain.Rent;
import com.buildingMachineRental.rent.domain.RentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentRepositoryInMemory implements RentRepository {

    private Map<Long, Rent> rentRepositoryMap = new HashMap<>();

    @Override
    public Rent getRent(Long machineId) {
        return rentRepositoryMap.get(machineId);
    }

    @Override
    public List<Rent> getAllRents() {
        return rentRepositoryMap.values().stream().toList();
    }

    @Override
    public List<Rent> getAllRentsByUserId(Long userId) {
        return rentRepositoryMap.values().stream()
                .filter(rent -> rent.getUserId().equals(userId))
                .toList();
    }

    @Override
    public List<Long> getAllRentedMachinesIds() {
        return rentRepositoryMap.values().stream()
                .map(rent -> rent.getMachineId())
                .toList();
    }

    @Override
    public List<Long> getAllRentedMachinesIdsByUser(Long userId) {
        return rentRepositoryMap.values().stream()
                .filter(rent -> rent.getUserId().equals(userId))
                .map(rent -> rent.getMachineId())
                .toList();
    }

    @Override
    public Rent addRent(Rent rent) {
        return rentRepositoryMap.put(rent.getRentId(), rent);
    }

    @Override
    public void deleteRent(Long machineId) {
        rentRepositoryMap.values().removeIf(rent -> rent.getMachineId().equals(machineId));
    }
}
