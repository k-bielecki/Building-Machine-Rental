package com.myshop.user.domain;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getUser(Long userId);

    User addUser(User user);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);
}
