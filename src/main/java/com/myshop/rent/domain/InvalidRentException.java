package com.myshop.rent.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidRentException extends RuntimeException {

    private final List<String> errors;

    public InvalidRentException(List<String> errors) {
        this.errors = errors;
    }
}
