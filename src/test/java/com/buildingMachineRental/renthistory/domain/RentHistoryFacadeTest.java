package com.buildingMachineRental.renthistory.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RentHistoryFacadeTest {

    private RentHistoryFacade rentHistoryFacade;

    @BeforeEach
    void onInit() {
        rentHistoryFacade = new RentHistoryFacadeConfiguration().inMemoryRepo();

        RentHistory rentHistory1 = RentHistory.builder()
                .rentHistoryId(1L)
                .userId(1L)
                .machineId(1L)
                .rentedDate(LocalDateTime.now())
                .returnedDate(LocalDateTime.now().plusDays(1))
                .build();

        RentHistory rentHistory2 = RentHistory.builder()
                .rentHistoryId(2L)
                .userId(2L)
                .machineId(2L)
                .rentedDate(LocalDateTime.now().minusDays(1))
                .returnedDate(LocalDateTime.now().plusDays(2))
                .build();

        RentHistory rentHistory3 = RentHistory.builder()
                .rentHistoryId(3L)
                .userId(2L)
                .machineId(3L)
                .rentedDate(LocalDateTime.now().minusDays(5))
                .returnedDate(LocalDateTime.now().plusDays(5))
                .build();

        rentHistoryFacade.addRentHistory(rentHistory1);
        rentHistoryFacade.addRentHistory(rentHistory2);
        rentHistoryFacade.addRentHistory(rentHistory3);
    }

    @Test
    void shouldGetAllRentHistory() {
        //given
        //when
        List<RentHistory> allRentHistory = rentHistoryFacade.getAllRentHistory();
        //then
        allRentHistory.forEach(System.out::println);
        assertThat(allRentHistory).hasSize(3);
    }

    @Test
    void shouldGetRentHistoryByUserId() {
        //given
        //when
        List<RentHistory> rentHistoryByUser1 = rentHistoryFacade.getRentHistoryByUser(1L);
        List<RentHistory> rentHistoryByUser2 = rentHistoryFacade.getRentHistoryByUser(2L);
        //then
        assertThat(rentHistoryByUser1).hasSize(1);
        assertThat(rentHistoryByUser2).hasSize(2);
    }

    @Test
    void shouldAddNewRentHistory() {
        //given
        RentHistory rentHistory4 = RentHistory.builder()
                .rentHistoryId(4L)
                .userId(2L)
                .machineId(3L)
                .rentedDate(LocalDateTime.now().minusDays(10))
                .returnedDate(LocalDateTime.now())
                .build();
        //when
        rentHistoryFacade.addRentHistory(rentHistory4);
        List<RentHistory> allRentHistory = rentHistoryFacade.getAllRentHistory();
        //then
        assertThat(allRentHistory).hasSize(4);
        assertThat(allRentHistory).contains(rentHistory4);
    }
}