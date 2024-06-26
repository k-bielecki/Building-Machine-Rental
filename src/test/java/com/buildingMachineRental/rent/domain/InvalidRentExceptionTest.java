package com.buildingMachineRental.rent.domain;

import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineFacade;
import com.buildingMachineRental.user.domain.User;
import com.buildingMachineRental.user.domain.UserFacade;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvalidRentExceptionTest {

    @InjectMocks
    private RentValidator rentValidator;

    @Mock
    private UserFacade userFacade;

    @Mock
    private MachineFacade machineFacade;

    @Nested
    class validateRent {

        @Test
        void shouldThrowExceptionWhenMachineNotExist() {
            //given
            Long machineId = 1L;
            Long userId = 1L;
            User user = User.builder().userId(userId).build();

            when(machineFacade.getMachineById(machineId)).thenReturn(null);
            when(userFacade.getUser(userId)).thenReturn(user);

            //when

            //then
            InvalidRentException e = assertThrows(InvalidRentException.class, () -> rentValidator.validateRent(machineId, userId));
            e.getErrors().forEach(System.out::println);
        }

        @Test
        void shouldThrowExceptionWhenUserNotExist() {
            //given
            Long machineId = 1L;
            Long userId = 1L;
            Machine machine = Machine.builder().id(machineId).rented(false).build();
            when(machineFacade.getMachineById(machineId)).thenReturn(machine);
            when(userFacade.getUser(userId)).thenReturn(null);
            //when

            //then
            InvalidRentException e = assertThrows(InvalidRentException.class, () -> rentValidator.validateRent(machineId, userId));
            e.getErrors().forEach(System.out::println);
        }

        @Test
        void shouldThrowExceptionWhenMachineIsAlreadyRented() {
            //given
            Long machineId = 1L;
            Long userId = 1L;
            Machine machine = Machine.builder().id(machineId).rented(true).build();
            User user = User.builder().userId(userId).build();

            when(machineFacade.getMachineById(machineId)).thenReturn(machine);
            when(userFacade.getUser(userId)).thenReturn(user);

            //when

            //then
            InvalidRentException e = assertThrows(InvalidRentException.class, () -> rentValidator.validateRent(machineId, userId));
            e.getErrors().forEach(System.out::println);
        }
    }

    @Nested
    class validateReturn {

        @Test
        void shouldThrowExceptionWhenMachineNotExist() {

            //given
            Long machineId = 1L;

            when(machineFacade.getMachineById(machineId)).thenReturn(null);

            //when

            //then
            InvalidRentException e = assertThrows(InvalidRentException.class, () -> rentValidator.validateReturn(machineId));
            e.getErrors().forEach(System.out::println);

        }

        @Test
        void shouldThrowExceptionWhenMachineIsNotRented() {
            //given
            Long machineId = 1L;
            Machine machine = Machine.builder().id(machineId).rented(false).build();

            when(machineFacade.getMachineById(machineId)).thenReturn(machine);

            //when

            //then
            InvalidRentException e = assertThrows(InvalidRentException.class, () -> rentValidator.validateReturn(machineId));
            e.getErrors().forEach(System.out::println);
        }
    }

}