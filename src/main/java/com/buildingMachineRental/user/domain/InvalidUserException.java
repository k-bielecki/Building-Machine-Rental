package com.buildingMachineRental.user.domain;

import java.util.List;

public class InvalidUserException extends RuntimeException{

    InvalidUserException(List<String> userErrors){
        super(userErrors.toString());
    }
}
