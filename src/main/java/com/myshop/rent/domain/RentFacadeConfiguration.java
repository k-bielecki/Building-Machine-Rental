package com.myshop.rent.domain;

import com.myshop.machine.domain.MachineFacade;
import com.myshop.renthistory.domain.RentHistoryFacade;
import com.myshop.user.domain.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentFacadeConfiguration {

    @Bean
    RentFacade rentFacade(MachineFacade machineFacade,
                          UserFacade userFacade,
                          RentRepository rentRepository,
                          RentHistoryFacade rentHistoryFacade) {
        RentValidator rentValidator = new RentValidator(machineFacade, userFacade);
        RentService rentService = new RentService(rentRepository, machineFacade, rentHistoryFacade, rentValidator);

        return new RentFacade(rentService);
    }
}
