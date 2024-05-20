package com.myshop.machine.infrastructure.api;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineFacade;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineController {

    private final MachineFacade machineFacade;

    public MachineController(MachineFacade machineFacade) {
        this.machineFacade = machineFacade;
    }

    @GetMapping("/machines")
    public List<Machine> getAllMachines(){
        return machineFacade.getAllMachines();
    }

    @GetMapping("/machines/{id}")
    public Machine getMachineById(@PathVariable Long id) {
        return machineFacade.getMachineById(id);
    }

    @PostMapping("/machines")
    public Machine addMachine(@RequestBody Machine machine){
        return machineFacade.addMachine(machine);
    }

    @PutMapping
    public Machine updateMachine(@PathVariable Long id, @RequestBody Machine machine){
        return machineFacade.updateMachine(id, machine);
    }

    @DeleteMapping
    public void deleteMachine(@PathVariable Long id){
        machineFacade.deleteMachineById(id);
    }
}
