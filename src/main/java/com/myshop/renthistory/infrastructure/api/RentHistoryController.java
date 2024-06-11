package com.myshop.renthistory.infrastructure.api;

import com.myshop.renthistory.domain.RentHistory;
import com.myshop.renthistory.domain.RentHistoryFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentHistoryController {

    private final RentHistoryFacade rentHistoryFacade;

    public RentHistoryController(RentHistoryFacade rentHistoryFacade) {
        this.rentHistoryFacade = rentHistoryFacade;
    }

    @GetMapping("/history")
    public List<RentHistory> getAllRentHistory() {
        return rentHistoryFacade.getAllRentHistory();
    }

    @GetMapping("/history/{userId}")
    public List<RentHistory> getRentHistoryByUserId(@PathVariable Long userId) {
        return rentHistoryFacade.getRentHistoryByUser(userId);
    }

    @PostMapping("/history")
    public RentHistory addRentHistory(@RequestBody RentHistoryDto rentHistoryDto) {
        return rentHistoryFacade.addRentHistory(mapFromDto(rentHistoryDto));
    }

    private RentHistory mapFromDto(RentHistoryDto rentHistoryDto) {
        return RentHistory.builder()
                .userId(rentHistoryDto.userId())
                .machineId(rentHistoryDto.machineId())
                .rentedDate(rentHistoryDto.rentedDate())
                .returnedDate(rentHistoryDto.returnedDate())
                .build();
    }
}
