package com.buildingMachineRental.renthistory.domain;

import com.buildingMachineRental.renthistory.infrastructure.database.RentHistoryRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentHistoryFacadeConfiguration {


    @Bean
    public RentHistoryFacade rentHistoryFacade (RentHistoryRepository rentHistoryRepository){
        RentHistoryService rentHistoryService = new RentHistoryService(rentHistoryRepository);
        return new RentHistoryFacade(rentHistoryService);
    }

    public RentHistoryFacade inMemoryRepo(){
        RentHistoryRepository rentHistoryRepository = new RentHistoryRepositoryInMemory();
        RentHistoryService rentHistoryService = new RentHistoryService(rentHistoryRepository);
        return new RentHistoryFacade(rentHistoryService);
    }
}
