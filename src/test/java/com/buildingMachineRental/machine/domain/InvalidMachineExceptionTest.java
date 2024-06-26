package com.buildingMachineRental.machine.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidMachineExceptionTest {

    private MachineFacade machineFacade;

    @BeforeEach
    void onInit() {
        machineFacade = new MachineFacadeConfig().inMemoryRepo();
    }

    @Nested
    class MachineValidation {
        @Test
        void shouldThrowExceptionWhenNameIsNotValid() {
            //given
            Machine machine = Machine.builder()
                    .id(1L)
                    .name("")
                    .pricePerDay(new BigDecimal(125))
                    .condition(MachineCondition.GOOD)
                    .rented(false)
                    .build();
            //when
            //then
            assertThrows(InvalidMachineException.class, () -> machineFacade.addMachine(machine));
        }

        @Test
        void shouldThrowExceptionWhenPriceIsNotValid() {
            //given
            Machine machine = Machine.builder()
                    .id(1L)
                    .name("Test")
                    .pricePerDay(new BigDecimal(-25))
                    .condition(MachineCondition.GOOD)
                    .rented(false)
                    .build();
            //when
            //then
            assertThrows(InvalidMachineException.class, () -> machineFacade.addMachine(machine));
        }

        @Test
        void shouldThrowExceptionWhenConditionIsNotValid() {
            //given
            Machine machine = Machine.builder()
                    .id(1L)
                    .name("Test")
                    .pricePerDay(new BigDecimal(125))
                    .rented(false)
                    .build();
            //when
            //then
            assertThrows(InvalidMachineException.class, () -> machineFacade.addMachine(machine));
        }
    }

    @Nested
    class MachineSortTypeValidation {

        @Test
        void shouldThrowExceptionWhenSortTypeIsNotValid() {
            //given
            //when
            //then
            assertThrows(InvalidMachineSortTypeException.class, () -> machineFacade.getSortedMachines("ASC", 0L, 25L));
        }
    }

    @Nested
    class MachinePaginationParametersException {

        @Test
        void shouldThrowExceptionWhenLimitIsOver100(){
            //given
            //when
            //then
            assertThrows(InvalidPaginationParametersException.class,() -> machineFacade.getSortedMachines("NAME_ASC", 0L, 200L));
        }

        @Test
        void shouldThrowExceptionWhenLimitIsBelow0(){
            //given
            //when
            //then
            assertThrows(InvalidPaginationParametersException.class,() -> machineFacade.getSortedMachines("NAME_ASC", 0L, -25L));
        }

        @Test
        void shouldThrowExceptionWhenOffsetIsBelow0(){
            //given
            //when
            //then
            assertThrows(InvalidPaginationParametersException.class,() -> machineFacade.getSortedMachines("NAME_ASC", -5L, 25L));
        }
    }


}