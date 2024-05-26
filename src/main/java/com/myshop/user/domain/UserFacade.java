package com.myshop.user.domain;

import java.util.List;

public class UserFacade {

    private final UserService userService;

    UserFacade(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUser(Long id) {
        return userService.getUser(id);
    }

    public User addUser(User user) {
        return userService.addUser(user);
    }

    public User updateUser(Long id, User user) {
        return userService.updateUser(id, user);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
