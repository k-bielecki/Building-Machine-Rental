package com.myshop.machine.domain;

import java.util.ArrayList;
import java.util.List;

public class InvalidMachineException extends RuntimeException{

    private final List<String> machineErrors;

    InvalidMachineException (List<String> machineErrors){
        this.machineErrors = machineErrors;
    }

    public List<String> getMachineErrors(){
        return machineErrors;
    }
}
