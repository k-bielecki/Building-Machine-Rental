package com.buildingMachineRental.rent.domain;

import com.buildingMachineRental.machine.domain.MachineFacade;
import com.buildingMachineRental.machine.domain.MachineFacadeConfig;
import com.buildingMachineRental.rent.infrastructure.database.RentRepositoryInMemory;
import com.buildingMachineRental.renthistory.domain.RentHistory;
import com.buildingMachineRental.renthistory.domain.RentHistoryFacade;
import com.buildingMachineRental.renthistory.domain.RentHistoryFacadeConfiguration;
import com.buildingMachineRental.user.domain.User;
import com.buildingMachineRental.user.domain.UserFacade;
import com.buildingMachineRental.user.domain.UserFacadeConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentFacadeConfiguration {

    @Bean
    public RentFacade rentFacade(MachineFacade machineFacade,
                          UserFacade userFacade,
                          RentRepository rentRepository,
                          RentHistoryFacade rentHistoryFacade) {
        RentValidator rentValidator = new RentValidator(machineFacade, userFacade);
        RentService rentService = new RentService(rentRepository, machineFacade, rentHistoryFacade, rentValidator);

        return new RentFacade(rentService);
    }

//    public RentFacade inMemoryRepo(){
//        RentRepository rentRepository = new RentRepositoryInMemory();
//        MachineFacade machineFacade = new MachineFacadeConfig().inMemoryRepo();
//        RentHistoryFacade rentHistoryFacade = new RentHistoryFacadeConfiguration().inMemoryRepo();
//        UserFacade userFacade = new UserFacadeConfiguration().inMemoryRepo();
//        RentValidator rentValidator = new RentValidator(machineFacade, userFacade);
//        RentService rentService = new RentService(rentRepository,machineFacade,rentHistoryFacade,rentValidator);
//
//        return new RentFacade(rentService);
//    }

}
