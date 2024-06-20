package com.buildingMachineRental.machine.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class MachineValidator {

    void validateMachine(Machine machine){
        List<String> errors = new ArrayList<>();

        if (!isNameValid(machine.getName())){
            errors.add("Invalid name of machine");
        }
        if (!isPriceValid(machine.getPricePerDay())){
            errors.add("Price must be greater than 0");
        }
        if (!isConditionValid(machine.getCondition())){
            errors.add("Set correct machine condition");
        }
        if (!errors.isEmpty()) {
            throw new InvalidMachineException(errors);
        }
    }

    private boolean isConditionValid(MachineCondition condition) {
        return condition != null;
    }

    private boolean isPriceValid(BigDecimal pricePerDay) {
        return pricePerDay.intValue() >= 0;
    }

    private boolean isNameValid(String name) {
        return name != null && !name.isEmpty();
    }
}
