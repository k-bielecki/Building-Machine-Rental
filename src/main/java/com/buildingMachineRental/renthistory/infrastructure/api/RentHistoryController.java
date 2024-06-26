package com.buildingMachineRental.renthistory.infrastructure.api;

import com.buildingMachineRental.renthistory.domain.RentHistory;
import com.buildingMachineRental.renthistory.domain.RentHistoryFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<RentHistoryDto> getAllRentHistory() {
        return rentHistoryFacade.getAllRentHistory().stream()
                .map(rentHistory -> mapRentHistoryToDto(rentHistory))
                .toList();
    }

    @GetMapping("/history/{userId}")
    public List<RentHistoryDto> getRentHistoryByUserId(@PathVariable Long userId) {
        return rentHistoryFacade.getRentHistoryByUser(userId).stream()
                .map(rentHistory -> mapRentHistoryToDto(rentHistory))
                .toList();
    }

    @PostMapping("/history")
    public RentHistory addRentHistory(@RequestBody RentHistoryDto rentHistoryDto) {
        return rentHistoryFacade.addRentHistory(mapRentHistoryFromDto(rentHistoryDto));
    }


}