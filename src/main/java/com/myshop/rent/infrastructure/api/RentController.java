package com.myshop.rent.infrastructure.api;

import com.myshop.machine.domain.Machine;
import com.myshop.rent.domain.InvalidRentException;
import com.myshop.rent.domain.Rent;
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

@RestController
public class RentController {

    private final RentFacade rentFacade;

    public RentController(RentFacade rentFacade) {
        this.rentFacade = rentFacade;
    }

    @GetMapping("/rents")
    public List<Rent> getAllRents() {
        return rentFacade.getAllRents();
    }

    @GetMapping("/rents/{userId}")
    public List<Rent> getAllRentsByUserId(@PathVariable Long userId) {
        return rentFacade.getAllRentsByUser(userId);
    }

    @GetMapping("/rents/machines")
    public List<Machine> getAllRentedMachines() {
        return rentFacade.getAllRentedMachines();
    }

    @GetMapping("/rents/machines/{userId}")
    public List<Machine> getAllRentedMachinesByUser(@PathVariable Long userId) {
        return rentFacade.getAllRentedMachinesByUser(userId);
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
