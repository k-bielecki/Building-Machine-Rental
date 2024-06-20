package com.buildingMachineRental.machine.infrastructure.api;

import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineFacade;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.buildingMachineRental.machine.infrastructure.api.MachineMapper.mapMachineFromDto;
import static com.buildingMachineRental.machine.infrastructure.api.MachineMapper.mapMachineToDto;

@RestController
public class MachineController {

    private static final Long EMPTY_ID = null;
    private final MachineFacade machineFacade;

    public MachineController(MachineFacade machineFacade) {
        this.machineFacade = machineFacade;
    }

//    @GetMapping("/machines")
//    public List<Machine> getAllMachines(){
//        return machineFacade.getAllMachines();
//    }

    @GetMapping("/machines")
    public List<MachineDto> getSortedMachines(@RequestParam(value = "sortType", required = false, defaultValue = "NAME_ASC") String machineSortType,
                                           @RequestParam(value = "offset", required = false, defaultValue = "0") Long offset,
                                           @RequestParam(value = "limit", required = false, defaultValue = "25") Long limit) {
        return machineFacade.getSortedMachines(machineSortType, offset, limit).stream()
                .map(machine -> mapMachineToDto(machine))
                .toList();
    }

    @GetMapping("/machines/{id}")
    public MachineDto getMachineById(@PathVariable Long id) {
        return mapMachineToDto(machineFacade.getMachineById(id));
    }

    @PostMapping("/machines")
    public Machine addMachine(@RequestBody MachineDto machineDto) {
        return machineFacade.addMachine(mapMachineFromDto(machineDto, EMPTY_ID));
    }


    @PutMapping("/machines/{id}")
    public Machine updateMachine(@PathVariable Long id, @RequestBody MachineDto machineDto) {
        return machineFacade.updateMachine(mapMachineFromDto(machineDto, id));
    }

    @DeleteMapping("/machines/{id}")
    public void deleteMachine(@PathVariable Long id) {
        machineFacade.deleteMachineById(id);
    }


}
