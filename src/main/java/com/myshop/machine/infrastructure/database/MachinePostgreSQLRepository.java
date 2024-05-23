package com.myshop.machine.infrastructure.database;

import com.myshop.machine.domain.InvalidMachineSortTypeException;
import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineRepository;
import com.myshop.machine.domain.MachineSortType;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MachinePostgreSQLRepository implements MachineRepository{

    private final MachinePostgreSQLRepositoryInterface postgreSQLDatabase;

    public MachinePostgreSQLRepository(MachinePostgreSQLRepositoryInterface postgreSQLDatabase) {
        this.postgreSQLDatabase = postgreSQLDatabase;
    }

    @Override
    public List<Machine> getAllMachines() {
        return postgreSQLDatabase.findAll();
    }

    @Override
    public List<Machine> getAllMachinesSortedWithPagination(MachineSortType machineSortType, Long offset, Long limit) {

        switch (machineSortType){
            case NAME_ASC:
                return postgreSQLDatabase.findByNameAscendingWithPagination(offset, limit);
            case NAME_DESC:
                return postgreSQLDatabase.findByNameDescendingWithPagination(offset, limit);
            case PRICE_ASC:
                return postgreSQLDatabase.findByPriceAscendingWithPagination(offset, limit);
            case PRICE_DESC:
                return postgreSQLDatabase.findByPriceDescendingWithPagination(offset, limit);
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
        return postgreSQLDatabase.findById(id).orElseThrow();
    }

    @Override
    public Machine addMachine(Machine machine) {
        return postgreSQLDatabase.save(machine);
    }

    @Override
    public Machine updateMachine(Machine machine) {
        return postgreSQLDatabase.save(machine);
    }

    @Override
    public void deleteMachineById(Long id) {
        postgreSQLDatabase.deleteById(id);
    }
}
