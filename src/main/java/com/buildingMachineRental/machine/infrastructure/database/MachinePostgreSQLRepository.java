package com.buildingMachineRental.machine.infrastructure.database;

import com.buildingMachineRental.machine.domain.InvalidMachineSortTypeException;
import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineRepository;
import com.buildingMachineRental.machine.domain.MachineSortType;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MachinePostgreSQLRepository implements MachineRepository{

    private final MachinePostgreSQLRepositoryInterface machinePostgreSQLDatabase;

    public MachinePostgreSQLRepository(MachinePostgreSQLRepositoryInterface machinePostgreSQLDatabase) {
        this.machinePostgreSQLDatabase = machinePostgreSQLDatabase;
    }

    @Override
    public List<Machine> getAllMachines() {
        return machinePostgreSQLDatabase.findAll();
    }

    @Override
    public List<Machine> getAllMachinesSortedWithPagination(MachineSortType machineSortType, Long offset, Long limit) {

        switch (machineSortType){
            case NAME_ASC:
                return machinePostgreSQLDatabase.findByNameAscendingWithPagination(offset, limit);
            case NAME_DESC:
                return machinePostgreSQLDatabase.findByNameDescendingWithPagination(offset, limit);
            case PRICE_ASC:
                return machinePostgreSQLDatabase.findByPriceAscendingWithPagination(offset, limit);
            case PRICE_DESC:
                return machinePostgreSQLDatabase.findByPriceDescendingWithPagination(offset, limit);
            default:
                StringBuilder availableSortTypes = new StringBuilder();
                Arrays.stream(MachineSortType.values())
                        .forEach(sortType -> {
                            availableSortTypes.append(sortType);
                            availableSortTypes.append(", ");
                        });
                throw new InvalidMachineSortTypeException("Available values: " + availableSortTypes);
        }
    }

    @Override
    public Machine getMachineById(Long id) {
        return machinePostgreSQLDatabase.findById(id).orElseThrow();
    }

    @Override
    public Machine addMachine(Machine machine) {
        return machinePostgreSQLDatabase.save(machine);
    }

    @Override
    public Machine updateMachine(Machine machine) {
        return machinePostgreSQLDatabase.save(machine);
    }

    @Override
    public void deleteMachineById(Long id) {
        machinePostgreSQLDatabase.deleteById(id);
    }
}
