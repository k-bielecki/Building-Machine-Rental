package com.buildingMachineRental.machine.infrastructure.api;

import com.buildingMachineRental.machine.domain.Machine;
import com.buildingMachineRental.machine.domain.MachineCategory;
import com.buildingMachineRental.machine.domain.MachineCondition;
import com.buildingMachineRental.machine.domain.MachineDetails;
import com.buildingMachineRental.utils.IntegrationTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class MachineControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnMachineWithId(){
        //given
        long id = 1L;
        MachineDto testMachine = MachineDto.builder()
                .name("test")
                .pricePerDay(new BigDecimal(100))
                .rented(false)
                .condition(MachineCondition.GOOD)
                .detailsDto(MachineDetailsDto.builder().machineHour(10).weight(new BigDecimal(1000)).horsePower(500).category(MachineCategory.BULLDOZER).build())
                .build();

        //when
        restTemplate.postForEntity("http://localhost:"+ port + "/machines", testMachine, String.class);
        ResponseEntity<MachineDto> responseFromController = restTemplate.getForEntity("http://localhost:"+ port + "/machines/" + id, MachineDto.class);

        //then
        assertEquals(responseFromController.getBody().getName(), testMachine.getName());
        assertEquals(responseFromController.getStatusCode(), HttpStatus.OK);
    }
}