package com.myshop.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class UserValidator {

    void validateNewUser(User user){
        List<String> errors = new ArrayList<>();

        if (!isFirstNameValid(user.getFirstName())){
            errors.add("First name is invalid!");
        }

        if (!isLastNameValid(user.getLastName())){
            errors.add("Last name is invalid!");
        }

        if (!isEmailValid(user.getEmail())){
            errors.add("Email is invalid!");
        }

        if (!isNicknameValid(user.getNickname())){
            errors.add("Nickname is invalid!");
        }

        if(!errors.isEmpty()){
            throw new InvalidUserException(errors);
        }
    }

    private boolean isFirstNameValid(String firstName){
        return Pattern.matches("[A-Z][a-z]{1,20}", firstName);
    }

    private boolean isLastNameValid(String lastName){
        return Pattern.matches("[A-Z][a-z]{1,20}", lastName);
    }

    private boolean isEmailValid (String email){
        return Pattern.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
    }
     private boolean isNicknameValid (String nickname){
        return Pattern.matches("^[a-zA-Z0-9_-]{6,20}$", nickname);
     }
}
