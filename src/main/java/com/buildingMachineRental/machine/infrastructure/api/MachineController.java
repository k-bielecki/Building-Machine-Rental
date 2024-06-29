package com.buildingMachineRental.machine.infrastructure.api;

import com.buildingMachineRental.machine.domain.InvalidMachineException;
import com.buildingMachineRental.machine.domain.InvalidMachineSortTypeException;
import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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

    @GetMapping("/machines")
    public ResponseEntity<List<MachineDto>> getSortedMachines(@RequestParam(value = "sortType", required = false, defaultValue = "NAME_ASC") String machineSortType,
                                                              @RequestParam(value = "offset", required = false, defaultValue = "0") Long offset,
                                                              @RequestParam(value = "limit", required = false, defaultValue = "25") Long limit) {

        List<MachineDto> response = machineFacade.getSortedMachines(machineSortType, offset, limit).stream()
                .map(MachineMapper::mapMachineToDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(InvalidMachineSortTypeException.class)
    private ResponseEntity<?> handleInvalidSortException(InvalidMachineSortTypeException e) {
        return ResponseEntity.unprocessableEntity().header("Error", e.getMessage()).build();
    }

    @GetMapping("/machines/{id}")
    public ResponseEntity<MachineDto> getMachineById(@PathVariable Long id) {
        return ResponseEntity.ok(mapMachineToDto(machineFacade.getMachineById(id)));
    }

    @PostMapping("/machines")
    public ResponseEntity<?> addMachine(@RequestBody MachineDto machineDto) {
        try {
            Machine machine = machineFacade.addMachine(mapMachineFromDto(machineDto, EMPTY_ID));
            URI uri = URI.create("/machines/" + machine.getId());

            return ResponseEntity.created(uri).build();

        } catch (InvalidMachineException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/machines/{id}")
    public ResponseEntity<?> updateMachine(@PathVariable Long id, @RequestBody MachineDto machineDto) {
        try {
            Machine machine = machineFacade.updateMachine(mapMachineFromDto(machineDto, id));
            URI uri = URI.create("/machines/" + machine.getId());

            return ResponseEntity.created(uri).build();

        } catch (InvalidMachineException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/machines/{id}")
    public ResponseEntity<?> deleteMachine(@PathVariable Long id) {
        machineFacade.deleteMachineById(id);
        return ResponseEntity.noContent().build();
    }
}
