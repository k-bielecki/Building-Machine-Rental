package com.myshop.machine.infrastructure.database;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineRepository;

import java.util.ArrayList;
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
    public Machine updateMachine(Long id, Machine machine) {
        return machineRepositoryMap.put(id, machine);
    }

    @Override
    public void deleteMachineById(Long id) {
        machineRepositoryMap.remove(id);
    }
}
