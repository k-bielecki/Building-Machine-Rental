package com.buildingMachineRental.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void validateNewUser(User user) {
        List<String> errors = new ArrayList<>();

        if (!isFirstNameValid(user.getFirstName())) {
            errors.add("First name is invalid!");
        }

        if (!isLastNameValid(user.getLastName())) {
            errors.add("Last name is invalid!");
        }

        if (!isEmailValid(user.getEmail())) {
            errors.add("Email is invalid!");
        }

        if (isEmailTaken(user.getEmail())) {
            errors.add("Email is taken!");
        }

        if (!isNicknameValid(user.getNickname())) {
            errors.add("Nickname is invalid!");
        }

        if (isNicknameTaken(user.getNickname())) {
            errors.add("Nickname is taken!");
        }

        if (!errors.isEmpty()) {
            throw new InvalidUserException(errors);
        }
    }

    void validateUpdatedUser(Long userId, User updatedUser) {
        List<String> errors = new ArrayList<>();

        if (!userId.equals(updatedUser.getUserId())) {
            errors.add("Invalid user id in body request");
        }

        if (!isFirstNameValid(updatedUser.getFirstName())) {
            errors.add("First name is invalid!");
        }

        if (!isLastNameValid(updatedUser.getLastName())) {
            errors.add("Last name is invalid!");
        }

        if (isEmailChanged(updatedUser.getUserId(), updatedUser.getEmail())) {
            errors.add("Email cannot be changed!");
        }

        if (isNicknameChanged(updatedUser.getUserId(), updatedUser.getNickname())) {
            errors.add("Nickname cannot be changed!");
        }

        if (!errors.isEmpty()) {
            throw new InvalidUserException(errors);
        }
    }

    private boolean isNicknameChanged(Long userId, String nickname) {
        User user = userRepository.getUser(userId);
        return !user.getNickname().equals(nickname);
    }

    private boolean isEmailChanged(Long userId, String email) {
        User user = userRepository.getUser(userId);
        return !user.getEmail().equals(email);
    }

    private boolean isFirstNameValid(String firstName) {
        return Pattern.matches("[A-Z][a-z]{1,20}", firstName);
    }

    private boolean isLastNameValid(String lastName) {
        return Pattern.matches("[A-Z][a-z]{1,20}", lastName);
    }

    private boolean isEmailValid(String email) {
        return Pattern.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
    }

    private boolean isEmailTaken(String email) {
        return userRepository.getAllUsers().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean isNicknameValid(String nickname) {
        return Pattern.matches("^[a-zA-Z0-9_-]{6,20}$", nickname);
    }

    private boolean isNicknameTaken(String nickname) {
        return userRepository.getAllUsers().stream()
                .anyMatch(user -> user.getNickname().equals(nickname));
    }
}
