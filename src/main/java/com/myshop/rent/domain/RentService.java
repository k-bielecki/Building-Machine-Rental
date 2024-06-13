package com.myshop.rent.domain;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineFacade;

import java.util.ArrayList;
import java.util.List;

class RentService {

    private final RentRepository rentRepository;
    private final MachineFacade machineFacade;

    RentService(RentRepository rentRepository, MachineFacade machineFacade) {
        this.rentRepository = rentRepository;
        this.machineFacade = machineFacade;
    }

    List<Rent> getAllRents() {
        return rentRepository.getAllRents();
    }

    List<Rent> getAllRentsByUser(Long userId) {
        return rentRepository.getAllRentsByUserId(userId);
    }

    List<Machine> getAllRentedMachine() {
        List<Machine> rentedMachines = new ArrayList<>();
        rentRepository.getAllRentedMachinesIds()
                .forEach(machineId -> rentedMachines.add(machineFacade.getMachineById(machineId)));

        return rentedMachines;
    }


}
