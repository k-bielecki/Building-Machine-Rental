package com.buildingMachineRental.machine.domain;

import java.util.List;

public interface MachineRepository {

    List<Machine> getAllMachines();

    List<Machine> getAllMachinesSortedWithPagination(MachineSortType machineSortType, Long offset, Long limit);

    Machine getMachineById(Long id);

    Machine addMachine(Machine machine);

    Machine updateMachine(Machine machine);

    void deleteMachineById(Long id);
}
