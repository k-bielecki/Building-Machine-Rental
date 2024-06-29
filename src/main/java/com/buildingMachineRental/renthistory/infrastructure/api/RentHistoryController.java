package com.buildingMachineRental.renthistory.infrastructure.api;

import com.buildingMachineRental.renthistory.domain.RentHistory;
import com.buildingMachineRental.renthistory.domain.RentHistoryFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.buildingMachineRental.renthistory.infrastructure.api.RentHistoryMapper.mapRentHistoryFromDto;
import static com.buildingMachineRental.renthistory.infrastructure.api.RentHistoryMapper.mapRentHistoryToDto;

@RestController
public class RentHistoryController {

    private final RentHistoryFacade rentHistoryFacade;

    public RentHistoryController(RentHistoryFacade rentHistoryFacade) {
        this.rentHistoryFacade = rentHistoryFacade;
    }

    @GetMapping("/history")
    public ResponseEntity<List<RentHistoryDto>> getAllRentHistory() {
        List<RentHistoryDto> response = rentHistoryFacade.getAllRentHistory().stream()
                .map(RentHistoryMapper::mapRentHistoryToDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<RentHistoryDto>> getRentHistoryByUserId(@PathVariable Long userId) {
        List<RentHistoryDto> response = rentHistoryFacade.getRentHistoryByUser(userId).stream()
                .map(RentHistoryMapper::mapRentHistoryToDto)
                .toList();
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/history")
//    public ResponseEntity<?> addRentHistory(@RequestBody RentHistoryDto rentHistoryDto) {
//        RentHistory rentHistory = rentHistoryFacade.addRentHistory(mapRentHistoryFromDto(rentHistoryDto));
//        return ResponseEntity.created(URI.create("/history/" + rentHistory.getRentHistoryId())).build();
//    }

}
