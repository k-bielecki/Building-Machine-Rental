package com.myshop.machine.domain;


import java.util.List;

class MachineService {

    private final MachineRepository machineRepository;
    private final MachineValidator machineValidator;

    MachineService(MachineRepository machineRepository, MachineValidator machineValidator) {
        this.machineRepository = machineRepository;
        this.machineValidator = machineValidator;
    }

    List<Machine> getAllMachines(){
        return machineRepository.getAllMachines();
    }

    Machine getMachineById(Long id){
        return machineRepository.getMachineById(id);
    }

    Machine addMachine(Machine machine){
        machineValidator.validateMachine(machine);
        return machineRepository.addMachine(machine);
    }

    Machine updateMachine(Long id, Machine machine){
        machineValidator.validateMachine(machine);
        return machineRepository.updateMachine(id, machine);
    }

    void deleteMachineById(Long id){
        machineRepository.deleteMachineById(id);
    }
}
