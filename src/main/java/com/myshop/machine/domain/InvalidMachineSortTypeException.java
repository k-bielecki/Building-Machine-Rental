package com.myshop.machine.domain;

public class InvalidMachineSortTypeException extends RuntimeException {

    public InvalidMachineSortTypeException(String message) {
        super ("Invalid machine sort type " + message);
    }
}
