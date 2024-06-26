package com.buildingMachineRental.user.domain;

import com.buildingMachineRental.user.infrastructure.database.UserRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFacadeConfiguration {

    @Bean
    public UserFacade userFacade(UserRepository userRepository) {
        UserValidator userValidator = new UserValidator(userRepository);
        UserService userService = new UserService(userRepository, userValidator);

        return new UserFacade(userService);
    }

    public UserFacade inMemoryRepo(){
        UserRepository userRepository = new UserRepositoryInMemory();
        UserValidator userValidator = new UserValidator(userRepository);
        UserService userService = new UserService(userRepository, userValidator);

        return new UserFacade(userService);
    }
}
