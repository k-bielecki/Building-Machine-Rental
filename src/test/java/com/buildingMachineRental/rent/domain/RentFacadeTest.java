package com.buildingMachineRental.rent.domain;

import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineFacade;
import com.buildingMachineRental.renthistory.domain.RentHistory;
import com.buildingMachineRental.renthistory.domain.RentHistoryFacade;
import com.buildingMachineRental.user.domain.User;
import com.buildingMachineRental.user.domain.UserFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentFacadeTest {

    private RentFacade rentFacade;

    @Mock
    private MachineFacade machineFacade;
    @Mock
    private UserFacade userFacade;
    @Mock
    private RentRepository rentRepository;
    @Mock
    private RentHistoryFacade rentHistoryFacade;


    @BeforeEach
    void setUp() {
        rentFacade = new RentFacadeConfiguration().rentFacade(
                machineFacade,
                userFacade,
                rentRepository,
                rentHistoryFacade
        );
    }

//    @BeforeEach
//    void onInit() {
//
//        Rent rent1 = Rent.builder()
//                .rentId(1L)
//                .machineId(1L)
//                .userId(1L)
//                .rentedDate(LocalDateTime.now().minusDays(3))
//                .build();
//
//        Rent rent2 = Rent.builder()
//                .rentId(2L)
//                .machineId(2L)
//                .userId(2L)
//                .rentedDate(LocalDateTime.now().minusDays(2))
//                .build();
//
//        Rent rent3 = Rent.builder()
//                .rentId(3L)
//                .machineId(3L)
//                .userId(2L)
//                .rentedDate(LocalDateTime.now().minusDays(1))
//                .build();
//
//        User testUser = User.builder()
//                .userId(1L)
//                .firstName("testFirsName")
//                .lastName("testLastName")
//                .email("test123@test.com")
//                .nickname("test123")
//                .password("secretpassword")
//                .build();
//
//        Machine testMachine = Machine.builder()
//                .id(1L)
//                .name("testName")
//                .pricePerDay(new BigDecimal(100))
//                .condition(MachineCondition.GOOD)
//                .rented(false)
//                .build();
//    }

    @Test
    void shouldGetAllRents() {
        //given
        Rent rent1 = Rent.builder().build();
        Rent rent2 = Rent.builder().build();
        List<Rent> rentList = Arrays.asList(rent1, rent2);
        when(rentRepository.getAllRents()).thenReturn(rentList);
        //when
        List<Rent> allRents = rentFacade.getAllRents();
        //then
        assertThat(allRents).hasSize(2);
        allRents.forEach(System.out::println);
    }

    @Test
    void shouldGetAllRentsByUserId() {
        //given
        Long userId = 1L;
        Rent rent1 = Rent.builder().userId(1L).build();
        Rent rent2 = Rent.builder().userId(2L).build();
        List<Rent> rentList = Arrays.asList(rent1, rent2);
        when(rentRepository.getAllRentsByUserId(userId)).thenReturn(rentList.stream()
                .filter(rent -> rent.getUserId().equals(userId))
                .toList());

        //when
        List<Rent> allRentsByUser = rentFacade.getAllRentsByUser(userId);

        //then
        assertThat(allRentsByUser).hasSize(1);
        assertThat(allRentsByUser).contains(rent1);
        verify(rentRepository).getAllRentsByUserId(userId);
    }

    @Test
    void shouldGetAllRentedMachines() {
        //given
        Machine machine1 = Machine.builder().id(1L).rented(true).build();
        Machine machine2 = Machine.builder().id(2L).rented(true).build();

        when(rentRepository.getAllRentedMachinesIds()).thenReturn(Arrays.asList(machine1.getId(), machine2.getId()));
        when(machineFacade.getMachineById(machine1.getId())).thenReturn(machine1);
        when(machineFacade.getMachineById(machine2.getId())).thenReturn(machine2);

        //when
        List<Machine> allRentedMachines = rentFacade.getAllRentedMachines();

        //then
        assertThat(allRentedMachines).hasSize(2);
        assertThat(allRentedMachines).contains(machine1, machine2);
        verify(rentRepository).getAllRentedMachinesIds();
    }

    @Test
    void shouldGetAllRentedMachinesByUser() {
        //given
        Long userId = 1L;
        Machine machine1 = Machine.builder().id(1L).rented(true).build();
        Machine machine2 = Machine.builder().id(2L).rented(true).build();

        when(rentRepository.getAllRentedMachinesIdsByUser(userId)).thenReturn(Arrays.asList(machine1.getId(), machine2.getId()));
        when(machineFacade.getMachineById(machine1.getId())).thenReturn(machine1);
        when(machineFacade.getMachineById(machine2.getId())).thenReturn(machine2);

        //when
        List<Machine> allRentedMachinesByUser = rentFacade.getAllRentedMachinesByUser(userId);

        //then
        assertThat(allRentedMachinesByUser).hasSize(2);
        assertThat(allRentedMachinesByUser).contains(machine1, machine2);
    }

    @Test
    void shouldRentMachine() {
        //given
        User testUser = User.builder()
                .userId(1L)
                .build();

        Machine testMachine = Machine.builder()
                .id(1L)
                .rented(false)
                .build();

        when(machineFacade.getMachineById(testMachine.getId())).thenReturn(testMachine);
        when(userFacade.getUser(testUser.getUserId())).thenReturn(testUser);

        List<Rent> rentList = new ArrayList<>();

        when(rentRepository.addRent(any(Rent.class))).thenAnswer(invocation -> {
            Rent rentToAdd = invocation.getArgument(0);
            rentList.add(rentToAdd);
            return rentToAdd;
        });

        when(rentFacade.getAllRents()).thenReturn(rentList);

        //when
        rentFacade.rentMachine(testMachine.getId(), testUser.getUserId());

        //then
        verify(rentRepository).addRent(any(Rent.class));
        List<Rent> allRents = rentFacade.getAllRents();
        allRents.forEach(System.out::println);
        assertThat(rentList).isNotEmpty();
    }

    @Test
    void shouldReturnMachine() {
        //given
        Long machineId = 1L;
        Long userId = 1L;
        Rent rent = Rent.builder()
                .machineId(machineId)
                .userId(userId)
                .build();
        Machine machine = Machine.builder()
                .id(machineId)
                .rented(true)
                .build();

        when(rentRepository.getRent(machineId)).thenReturn(rent);
        when(machineFacade.getMachineById(machineId)).thenReturn(machine);

        //when
        rentFacade.returnMachine(machineId);

        //then
        verify(rentRepository).getRent(machineId);
        verify(rentHistoryFacade).addRentHistory(any(RentHistory.class));
        verify(rentRepository).deleteRent(machineId);
        verify(machineFacade).updateMachine(any(Machine.class));
    }
}