package com.myshop.rent.infrastructure.api;

import com.myshop.machine.infrastructure.api.MachineDto;
import com.myshop.machine.infrastructure.api.MachineMapper;
import com.myshop.rent.domain.InvalidRentException;
import com.myshop.rent.domain.RentFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.myshop.rent.infrastructure.api.RentMapper.*;

@RestController
public class RentController {

    private final RentFacade rentFacade;

    public RentController(RentFacade rentFacade) {
        this.rentFacade = rentFacade;
    }

    @GetMapping("/rents")
    public List<RentDto> getAllRents() {
        return rentFacade.getAllRents().stream()
                .map(rent -> mapRentToDto(rent))
                .toList();
    }

    @GetMapping("/rents/{userId}")
    public List<RentDto> getAllRentsByUserId(@PathVariable Long userId) {
        return rentFacade.getAllRentsByUser(userId).stream()
                .map(rent -> mapRentToDto(rent))
                .toList();
    }

    @GetMapping("/rents/machines")
    public List<MachineDto> getAllRentedMachines() {
        return rentFacade.getAllRentedMachines().stream()
                .map(machine -> MachineMapper.mapMachineToDto(machine))
                .toList();
    }

    @GetMapping("/rents/machines/{userId}")
    public List<MachineDto> getAllRentedMachinesByUser(@PathVariable Long userId) {
        return rentFacade.getAllRentedMachinesByUser(userId).stream()
                .map(machine -> MachineMapper.mapMachineToDto(machine))
                .toList();
    }

    @PostMapping("/rent")
    public ResponseEntity rentMachine(@RequestParam Long machineId, @RequestParam Long userId) {
        try {
            rentFacade.rentMachine(machineId, userId);
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getErrors());
        }
        return ResponseEntity.created(URI.create("/rent/" + machineId)).build();
    }

    @PutMapping("/return")
    public ResponseEntity returnMachine(@RequestParam Long machineId) {
        try {
            rentFacade.returnMachine(machineId);
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getErrors());
        }
        return ResponseEntity.ok().build();
    }




}
