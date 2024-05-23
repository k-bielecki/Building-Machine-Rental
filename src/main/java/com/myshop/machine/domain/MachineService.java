package com.myshop.machine.domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MachineService {

    private final MachineRepository machineRepository;
    private final MachineValidator machineValidator;

    MachineService(MachineRepository machineRepository, MachineValidator machineValidator) {
        this.machineRepository = machineRepository;
        this.machineValidator = machineValidator;
    }

    List<Machine> getAllMachines() {
        return machineRepository.getAllMachines();
    }

    List<Machine> getAllMachinesSortedWithPagination(String machineSortType, Long offset, Long limit) {
        paginationValidator(offset, limit);
        MachineSortType parsedMachineSortType = parseMachineSortType(machineSortType);
        return machineRepository.getAllMachinesSortedWithPagination(parsedMachineSortType, offset, limit);

    }

    Machine getMachineById(Long id) {
        return machineRepository.getMachineById(id);
    }

    Machine addMachine(Machine machine) {
        machineValidator.validateMachine(machine);
        return machineRepository.addMachine(machine);
    }

    Machine updateMachine(Machine machine) {
        machineValidator.validateMachine(machine);
        return machineRepository.updateMachine(machine);
    }

    void deleteMachineById(Long id) {
        machineRepository.deleteMachineById(id);
    }

    private void paginationValidator(Long offset, Long limit) {
        List<String> errors = new ArrayList<>();
        if (limit > 100) {
            errors.add("Limit cannot be higher than 100");
        }

        if (limit < 0 || offset < 0) {
            errors.add("Limit/Offset cannot be lower than 0");
        }

        if (!errors.isEmpty()) {
            throw new InvalidPaginationParametersException(errors.toString());
        }
    }

    private MachineSortType parseMachineSortType(String machineSortType) {
        switch (machineSortType) {
            case "NAME_ASC":
                return MachineSortType.NAME_ASC;
            case "NAME_DESC":
                return MachineSortType.NAME_DESC;
            case "PRICE_ASC":
                return MachineSortType.PRICE_ASC;
            case "PRICE_DESC":
                return MachineSortType.PRICE_DESC;
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
