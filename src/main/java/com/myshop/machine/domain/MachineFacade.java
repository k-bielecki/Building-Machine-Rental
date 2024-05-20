package com.myshop.machine.domain;

import java.util.List;

public class MachineFacade {

    private final MachineService machineService;

    MachineFacade(MachineService machineService) {
        this.machineService = machineService;
    }

    public List<Machine> getAllMachines(){
        return machineService.getAllMachines();
    }

    public Machine getMachineById(Long id){
        return machineService.getMachineById(id);
    }

    public Machine addMachine(Machine machine){
        return machineService.addMachine(machine);
    }

    public Machine updateMachine(Long id, Machine machine){
        return machineService.updateMachine(id, machine);
    }

    public void deleteMachineById(Long id){
        machineService.deleteMachineById(id);
    }
}
