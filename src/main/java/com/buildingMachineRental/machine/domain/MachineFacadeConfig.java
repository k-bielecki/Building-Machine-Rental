package com.buildingMachineRental.machine.domain;

import com.buildingMachineRental.machine.infrastructure.database.MachineRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MachineFacadeConfig {

    @Bean
    public MachineFacade machineFacade(MachineRepository machineRepository){
        MachineValidator machineValidator = new MachineValidator();
        MachineService machineService = new MachineService(machineRepository, machineValidator);
        return new MachineFacade(machineService);
    }

    public MachineFacade inMemoryRepo(){
        MachineRepository machineRepository = new MachineRepositoryInMemory();
        MachineValidator machineValidator = new MachineValidator();
        MachineService machineService = new MachineService(machineRepository, machineValidator);
        return new MachineFacade(machineService);
    }
}
