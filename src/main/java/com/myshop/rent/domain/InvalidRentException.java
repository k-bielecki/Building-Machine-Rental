package com.myshop.rent.domain;

import java.util.List;

class InvalidRentException extends RuntimeException {

    InvalidRentException(List<String> errors) {
        super(errors.toString());
    }
}
