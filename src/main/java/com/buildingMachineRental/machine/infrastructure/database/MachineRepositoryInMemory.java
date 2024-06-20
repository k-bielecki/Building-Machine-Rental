package com.buildingMachineRental.machine.infrastructure.database;

import com.buildingMachineRental.machine.domain.InvalidMachineSortTypeException;
import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineRepository;
import com.buildingMachineRental.machine.domain.MachineSortType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachineRepositoryInMemory implements MachineRepository {

    private final Map<Long, Machine> machineRepositoryMap = new HashMap<>();

    @Override
    public List<Machine> getAllMachines() {
        return machineRepositoryMap.values().stream().toList();
    }

    @Override
    public List<Machine> getAllMachinesSortedWithPagination(MachineSortType machineSortType, Long offset, Long limit) {
        return machineRepositoryMap.values().stream()
                .sorted(getComparator(machineSortType))
                .skip(offset)
                .limit(limit)
                .toList();
    }

    @Override
    public Machine getMachineById(Long id) {
        if (machineRepositoryMap.containsKey(id)) {
            return machineRepositoryMap.get(id);
        } else {
            throw new IllegalArgumentException("ID doesn't exist");
        }
    }

    @Override
    public Machine addMachine(Machine machine) {
        return machineRepositoryMap.put(machine.getId(), machine);
    }

    @Override
    public Machine updateMachine(Machine machine) {
        return machineRepositoryMap.put(machine.getId(), machine);
    }

    @Override
    public void deleteMachineById(Long id) {
        machineRepositoryMap.remove(id);
    }

    private Comparator<Machine> getComparator(MachineSortType machineSortType) {
        switch (machineSortType) {
            case NAME_ASC:
                return Comparator.comparing(Machine::getName);
            case NAME_DESC:
                return Comparator.comparing(Machine::getName).reversed();
            case PRICE_ASC:
                return Comparator.comparing(Machine::getPricePerDay);
            case PRICE_DESC:
                return Comparator.comparing(Machine::getPricePerDay).reversed();
            default:
                StringBuilder availableSortTypes = new StringBuilder();
                Arrays.stream(MachineSortType.values())
                        .forEach(sortType -> {
                            availableSortTypes.append(sortType);
                            availableSortTypes.append(", ");
                        });
                throw new InvalidMachineSortTypeException("Available sort types: " + availableSortTypes);
        }
    }
}
