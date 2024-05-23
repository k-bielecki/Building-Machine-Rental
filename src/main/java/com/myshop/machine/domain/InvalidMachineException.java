package com.myshop.machine.domain;

import java.util.List;

public class InvalidMachineException extends RuntimeException{

    InvalidMachineException (List<String> machineErrors){
        super(machineErrors.toString());
    }
}
