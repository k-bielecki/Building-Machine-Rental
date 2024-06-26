package com.buildingMachineRental.machine.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MachineFacadeTest {

    private MachineFacade machineFacade;

    @BeforeEach
    void onInit() {
        machineFacade = new MachineFacadeConfig().inMemoryRepo();
    }

    @Nested
    class CRUDTest {

        @BeforeEach
        void onInit() {
            Machine machine1 = Machine.builder()
                    .id(1L)
                    .name("Komatsu")
                    .pricePerDay(new BigDecimal(125))
                    .condition(MachineCondition.GOOD)
                    .rented(false)
                    .build();

            Machine machine2 = Machine.builder()
                    .id(2L)
                    .name("JBC")
                    .pricePerDay(new BigDecimal(250))
                    .condition(MachineCondition.GOOD)
                    .rented(false)
                    .build();

            Machine machine3 = Machine.builder()
                    .id(3L)
                    .name("Case")
                    .pricePerDay(new BigDecimal(150))
                    .condition(MachineCondition.GOOD)
                    .rented(false)
                    .build();

            machineFacade.addMachine(machine1);
            machineFacade.addMachine(machine2);
            machineFacade.addMachine(machine3);

        }

        @Test
        void shouldGetAllMachines() {
            //given

            //when
            List<Machine> machines = machineFacade.getAllMachines();
            //then
            assertThat(machines).hasSize(3);
        }

        @Test
        void shouldGetMachineById() {
            //given

            //when
            Machine result = machineFacade.getMachineById(1L);
            //then
            assertEquals(1L, result.getId());
        }

        @Test
        void shouldAddMachine() {
            //given
            Machine newMachine = Machine.builder()
                    .id(4L)
                    .name("Scania")
                    .pricePerDay(new BigDecimal(100))
                    .condition(MachineCondition.USED)
                    .rented(false)
                    .build();
            //when
            machineFacade.addMachine(newMachine);
            //then
            assertThat(machineFacade.getAllMachines()).hasSize(4);
        }

        @Test
        void shouldUpdateMachine() {
            //given
            //when
            machineFacade.updateMachine(Machine.builder().id(1L).name("KomatsuUpdated").pricePerDay(new BigDecimal(140)).condition(MachineCondition.GOOD).rented(false).build());
            Machine result = machineFacade.getMachineById(1L);
            //then
            assertEquals(1L, result.getId());
            assertEquals("KomatsuUpdated", result.getName());
            assertEquals(new BigDecimal(140), result.getPricePerDay());
            assertEquals(MachineCondition.GOOD, result.getCondition());
            assertEquals(false, result.getRented());
        }

        @Test
        void shouldDeleteMachineById() {
            //given
            //when
            machineFacade.deleteMachineById(1L);
            //then
            assertThat(machineFacade.getAllMachines()).hasSize(2);
        }

        @Test
        void shouldGetAllMachinesSortedAscendingByName() {
            //given
            //when
            List<Machine> machinesSortedAscending = machineFacade.getSortedMachines("NAME_ASC", 0L, 25L);
            //then
            assertAll(
                    () -> assertTrue(machinesSortedAscending.get(0).getName().startsWith("C")),
                    () -> assertTrue(machinesSortedAscending.get(1).getName().startsWith("J")),
                    () -> assertTrue(machinesSortedAscending.get(2).getName().startsWith("K")),
                    () -> assertEquals(3, machinesSortedAscending.size())
            );
        }

        @Test
        void shouldGetAllMachinesSortedDescendingByName() {
            //given
            //when
            List<Machine> machinesSortedAscending = machineFacade.getSortedMachines("NAME_DESC", 0L, 25L);
            //then
            assertAll(
                    () -> assertTrue(machinesSortedAscending.get(0).getName().startsWith("K")),
                    () -> assertTrue(machinesSortedAscending.get(1).getName().startsWith("J")),
                    () -> assertTrue(machinesSortedAscending.get(2).getName().startsWith("C")),
                    () -> assertEquals(3, machinesSortedAscending.size())
            );
        }

        @Test
        void shouldGetAllMachinesSortedAscendingByPrice() {
            //given
            //when
            List<Machine> machinesSortedAscending = machineFacade.getSortedMachines("PRICE_ASC", 0L, 25L);
            //then
            assertAll(
                    () -> assertTrue(machinesSortedAscending.get(0).getName().startsWith("K")),
                    () -> assertTrue(machinesSortedAscending.get(1).getName().startsWith("C")),
                    () -> assertTrue(machinesSortedAscending.get(2).getName().startsWith("J")),
                    () -> assertEquals(3, machinesSortedAscending.size())
            );
        }

        @Test
        void shouldGetAllMachinesSortedDescendingByPrice() {
            //given
            //when
            List<Machine> machinesSortedAscending = machineFacade.getSortedMachines("PRICE_DESC", 0L, 25L);
            //then
            assertAll(
                    () -> assertTrue(machinesSortedAscending.get(0).getName().startsWith("J")),
                    () -> assertTrue(machinesSortedAscending.get(1).getName().startsWith("C")),
                    () -> assertTrue(machinesSortedAscending.get(2).getName().startsWith("K")),
                    () -> assertEquals(3, machinesSortedAscending.size())
            );
        }
    }
}