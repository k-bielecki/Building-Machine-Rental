package com.buildingMachineRental.rent.infrastructure.api;

import com.buildingMachineRental.machine.infrastructure.api.MachineDto;
import com.buildingMachineRental.machine.infrastructure.api.MachineMapper;
import com.buildingMachineRental.rent.domain.InvalidRentException;
import com.buildingMachineRental.rent.domain.RentFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.buildingMachineRental.rent.infrastructure.api.RentMapper.mapRentToDto;

@RestController
public class RentController {

    private final RentFacade rentFacade;

    public RentController(RentFacade rentFacade) {
        this.rentFacade = rentFacade;
    }

    @GetMapping("/rents")
    public ResponseEntity<List<RentDto>> getAllRents() {
        List<RentDto> response = rentFacade.getAllRents().stream()
                .map(RentMapper::mapRentToDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/rents/{userId}")
    public ResponseEntity<List<RentDto>> getAllRentsByUserId(@PathVariable Long userId) {
        List<RentDto> response = rentFacade.getAllRentsByUser(userId).stream()
                .map(RentMapper::mapRentToDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/rents/machines")
    public ResponseEntity<List<MachineDto>> getAllRentedMachines() {
        List<MachineDto> response = rentFacade.getAllRentedMachines().stream()
                .map(MachineMapper::mapMachineToDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/rents/machines/{userId}")
    public ResponseEntity<List<MachineDto>> getAllRentedMachinesByUser(@PathVariable Long userId) {
        List<MachineDto> response = rentFacade.getAllRentedMachinesByUser(userId).stream()
                .map(MachineMapper::mapMachineToDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rentMachine(@RequestParam Long machineId, @RequestParam Long userId) {
        try {
            rentFacade.rentMachine(machineId, userId);
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getErrors());
        }
        URI uri = URI.create("/rent/" + machineId);

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/return")
    public ResponseEntity<?> returnMachine(@RequestParam Long machineId) {
        try {
            rentFacade.returnMachine(machineId);
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getErrors());
        }
        return ResponseEntity.ok().build();
    }

}
