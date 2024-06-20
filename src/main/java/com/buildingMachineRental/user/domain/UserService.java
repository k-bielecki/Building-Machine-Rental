package com.buildingMachineRental.user.domain;

import java.util.List;


class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    User getUser(Long id){
        return userRepository.getUser(id);
    }

    User addUser(User user){
        userValidator.validateNewUser(user);
        return userRepository.addUser(user);
    }

    User updateUser(Long id, User user){
        userValidator.validateUpdatedUser(id, user);
        return userRepository.updateUser(user);
    }

    void deleteUser(Long id){
        userRepository.deleteUser(id);
    }
}
