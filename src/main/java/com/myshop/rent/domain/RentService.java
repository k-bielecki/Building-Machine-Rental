package com.myshop.rent.domain;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineFacade;
import com.myshop.renthistory.domain.RentHistory;
import com.myshop.renthistory.domain.RentHistoryFacade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class RentService {

    private final RentRepository rentRepository;
    private final MachineFacade machineFacade;
    private final RentHistoryFacade rentHistoryFacade;
    private final RentValidator rentValidator;

    RentService(RentRepository rentRepository, MachineFacade machineFacade, RentHistoryFacade rentHistoryFacade, RentValidator rentValidator) {
        this.rentRepository = rentRepository;
        this.machineFacade = machineFacade;
        this.rentHistoryFacade = rentHistoryFacade;
        this.rentValidator = rentValidator;
    }

    List<Rent> getAllRents() {
        return rentRepository.getAllRents();
    }

    List<Rent> getAllRentsByUser(Long userId) {
        return rentRepository.getAllRentsByUserId(userId);
    }

    List<Machine> getAllRentedMachines() {
        List<Machine> rentedMachines = new ArrayList<>();
        rentRepository.getAllRentedMachinesIds()
                .forEach(machineId -> rentedMachines.add(machineFacade.getMachineById(machineId)));

        return rentedMachines;
    }

    List<Machine> getAllRentedMachinesByUser(Long userId) {
        List<Machine> rentedMachines = new ArrayList<>();
        rentRepository.getAllRentedMachinesIdsByUser(userId)
                .forEach(machineId -> rentedMachines.add(machineFacade.getMachineById(machineId)));

        return rentedMachines;
    }

    void rentMachine(Long machineId, Long userId) {
        rentValidator.validateRent(machineId, userId);
        addRentToRepository(machineId, userId);
        setMachineStatusAsRented(machineId);
    }

    void returnMachine(Long machineId) {
        rentValidator.validateReturn(machineId);
        addRentToRentHistoryRepository(machineId);
        removeRentFromRepository(machineId);
        setMachineStatusAsReturned(machineId);
    }

    private void addRentToRentHistoryRepository(Long machineId) {
        Rent rent = rentRepository.getRent(machineId);
        rentHistoryFacade.addRentHistory(RentHistory.builder()
                .userId(rent.getUserId())
                .machineId(rent.getMachineId())
                .rentedDate(rent.getRentedDate())
                .returnedDate(LocalDateTime.now())
                .build());
    }

    private void removeRentFromRepository(Long machineId) {
        rentRepository.deleteRent(machineId);
    }

    private void setMachineStatusAsRented(Long machineId) {
        setMachineRentStatus(machineId, true);
    }

    private void setMachineStatusAsReturned(Long machineId) {
        setMachineRentStatus(machineId, false);
    }

    private void setMachineRentStatus(Long machineId, boolean rented) {
        Machine machine = machineFacade.getMachineById(machineId);
        machineFacade.updateMachine(Machine.builder()
                .id(machine.getId())
                .name(machine.getName())
                .pricePerDay(machine.getPricePerDay())
                .rented(rented)
                .condition(machine.getCondition())
                .details(machine.getDetails())
                .build());
    }

    private void addRentToRepository(Long machineId, Long userId) {
        rentRepository.addRent(Rent.builder()
                .machineId(machineId)
                .userId(userId)
                .rentedDate(LocalDateTime.now())
                .build());
    }

}
