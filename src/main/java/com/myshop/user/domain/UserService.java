package com.myshop.user.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class UserService {

    private UserRepository userRepository;

    List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    User getUser(Long id){
        return userRepository.getUser(id);
    }

    User addUser(User user){
        return userRepository.addUser(user);
    }

    User updateUser(Long id, User user){
        return userRepository.updateUser(id, user);
    }

    void deleteUser(Long id){
        userRepository.deleteUser(id);
    }
}
