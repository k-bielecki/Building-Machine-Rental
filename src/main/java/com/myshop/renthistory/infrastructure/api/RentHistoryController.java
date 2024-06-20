package com.myshop.renthistory.infrastructure.api;

import com.myshop.renthistory.domain.RentHistory;
import com.myshop.renthistory.domain.RentHistoryFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myshop.renthistory.infrastructure.api.RentHistoryMapper.*;

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
