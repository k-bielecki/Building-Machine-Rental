package com.myshop.rent.infrastructure.api;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.infrastructure.api.MachineDetailsDto;
import com.myshop.machine.infrastructure.api.MachineDto;
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
    public List<RentDto> getAllRents() {
        return rentFacade.getAllRents().stream()
                .map(rent -> toDto(rent))
                .toList();
    }

    @GetMapping("/rents/{userId}")
    public List<RentDto> getAllRentsByUserId(@PathVariable Long userId) {
        return rentFacade.getAllRentsByUser(userId).stream()
                .map(rent -> toDto(rent))
                .toList();
    }

    @GetMapping("/rents/machines")
    public List<MachineDto> getAllRentedMachines() {
        return rentFacade.getAllRentedMachines().stream()
                .map(machine -> MachineDto.builder()
                        .name(machine.getName())
                        .pricePerDay(machine.getPricePerDay())
                        .rented(machine.getRented())
                        .condition(machine.getCondition())
                        .detailsDto(MachineDetailsDto.builder()
                                .machineHour(machine.getDetails().getMachineHour())
                                .weight(machine.getDetails().getWeight())
                                .horsePower(machine.getDetails().getHorsePower())
                                .category(machine.getDetails().getCategory())
                                .build())
                        .build())
                .toList();
    }

    @GetMapping("/rents/machines/{userId}")
    public List<MachineDto> getAllRentedMachinesByUser(@PathVariable Long userId) {
        return rentFacade.getAllRentedMachinesByUser(userId).stream()
                .map(machine -> MachineDto.builder()
                        .name(machine.getName())
                        .pricePerDay(machine.getPricePerDay())
                        .rented(machine.getRented())
                        .condition(machine.getCondition())
                        .detailsDto(MachineDetailsDto.builder()
                                .machineHour(machine.getDetails().getMachineHour())
                                .weight(machine.getDetails().getWeight())
                                .horsePower(machine.getDetails().getHorsePower())
                                .category(machine.getDetails().getCategory())
                                .build())
                        .build())
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

    private RentDto toDto(Rent rent) {
        return new RentDto(rent.getUserId(), rent.getMachineId());
    }


}
