package com.myshop.rent.domain;

import com.myshop.machine.domain.Machine;
import com.myshop.machine.domain.MachineFacade;
import com.myshop.user.domain.User;
import com.myshop.user.domain.UserFacade;

import java.util.ArrayList;
import java.util.List;

class RentValidator {

    private final MachineFacade machineFacade;
    private final UserFacade userFacade;

    RentValidator(MachineFacade machineFacade, UserFacade userFacade) {
        this.machineFacade = machineFacade;
        this.userFacade = userFacade;
    }

    void validateRent(Long machineId, Long userId) {
        List<String> errors = new ArrayList<>();
        Machine machine = machineFacade.getMachineById(machineId);
        User user = userFacade.getUser(userId);

        if (machine == null) {
            errors.add("Machine does not exist!");
        }

        if (machine != null && machine.getRented()) {
            errors.add("Machine is already rented!");
        }

        if (user == null) {
            errors.add("User does not exist!");
        }

        if (!errors.isEmpty()) {
            throw new InvalidRentException(errors);
        }
    }

    void validateReturn(Long machineId) {
        List<String> errors = new ArrayList<>();
        Machine machine = machineFacade.getMachineById(machineId);

        if (machine == null) {
            errors.add("Machine does not exist!");
        }

        if (machine != null && !machine.getRented()) {
            errors.add("Machine is already returned!");
        }

        if (!errors.isEmpty()) {
            throw new InvalidRentException(errors);
        }
    }
}
