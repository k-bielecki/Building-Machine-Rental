package com.myshop.machine.infrastructure.api;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineDetails;
import com.myshop.machine.domain.MachineFacade;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineController {

    private static final Long EMPTY_ID = null;
    private final MachineFacade machineFacade;

    public MachineController(MachineFacade machineFacade) {
        this.machineFacade = machineFacade;
    }

    @GetMapping("/machines")
    public List<Machine> getAllMachines(){
        return machineFacade.getAllMachines();
    }

    @GetMapping("/machines")
    public List<Machine> getSortedMachines (@RequestParam(value = "sortType", required = false, defaultValue = "NAME_ASC") String machineSortType,
                                            @RequestParam(value = "offset", required = false, defaultValue = "0") Long offset,
                                            @RequestParam(value = "limit", required = false, defaultValue = "25") Long limit){
        return machineFacade.getSortedMachines(machineSortType, offset, limit);
    }

    @GetMapping("/machines/{id}")
    public Machine getMachineById(@PathVariable Long id) {
        return machineFacade.getMachineById(id);
    }

    @PostMapping("/machines")
    public Machine addMachine(@RequestBody Machine machine){
        return machineFacade.addMachine(machine);
    }

    @PutMapping("/machines/{id}")
    public Machine updateMachine(@PathVariable Long id, @RequestBody Machine machine){
        return machineFacade.updateMachine(id, machine);
    }

    @DeleteMapping("/machines/{id}")
    public void deleteMachine(@PathVariable Long id){
        machineFacade.deleteMachineById(id);
    }
}
