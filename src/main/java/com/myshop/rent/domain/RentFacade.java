package com.myshop.rent.domain;

import com.myshop.machine.domain.Machine;

import java.util.List;

public class RentFacade {

    private final RentService rentService;

    public RentFacade(RentService rentService) {
        this.rentService = rentService;
    }

    public List<Rent> getAllRents(){
        return rentService.getAllRents();
    }

    public List<Rent> getAllRentsByUser(Long userId){
        return rentService.getAllRentsByUser(userId);
    }

    public List<Machine> getAllRentedMachines(){
        return rentService.getAllRentedMachines();
    }

    public List<Machine> getAllRentedMachinesByUser(Long userId){
        return rentService.getAllRentedMachinesByUser(userId);
    }

    public void rentMachine(Long machineId, Long userId){
        rentService.rentMachine(machineId, userId);
    }

    public void returnMachine(Long machineId){
        rentService.returnMachine(machineId);
    }
}
