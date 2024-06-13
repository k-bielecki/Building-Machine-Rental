package com.myshop.rent.domain;

import java.util.List;

public interface RentRepository {

    Rent getRent(Long machineId);

    List<Rent> getAllRents();

    List<Rent> getAllRentsByUserId(Long userId);

    List<Long> getAllRentedMachinesIds();

    List<Long> getAllRentedMachinesIdsByUser(Long userId);

    Rent addRent(Rent rent);

    void deleteRent(Long machineId);
}
