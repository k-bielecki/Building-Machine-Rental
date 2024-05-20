package com.myshop.machine.infrastructure.database;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineRepository;

import java.util.List;

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
    public Machine getMachineById(Long id) {
        return postgreSQLDatabase.findById(id).orElseThrow();
    }

    @Override
    public Machine addMachine(Machine machine) {
        return postgreSQLDatabase.save(machine);
    }

    @Override
    public Machine updateMachine(Long id, Machine machine) {
        return postgreSQLDatabase.save(machine);
    }

    @Override
    public void deleteMachineById(Long id) {
        postgreSQLDatabase.deleteById(id);
    }
}
