package com.myshop.user.infrastructure.database;

import com.myshop.user.domain.User;
import com.myshop.user.domain.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository {

    private final Map<Long, User> userRepositoryMap = new HashMap<>();

    @Override
    public List<User> getAllUsers() {
        return userRepositoryMap.values().stream().toList();
    }

    @Override
    public User getUser(Long userId) {
        if(userRepositoryMap.containsKey(userId)){
            return userRepositoryMap.get(userId);
        } else {
            throw new IllegalArgumentException("ID doesn't exist");
        }
    }

    @Override
    public User addUser(User user) {
        return userRepositoryMap.put(user.getUserId(), user);
    }

    @Override
    public User updateUser(User user) {
        return userRepositoryMap.put(user.getUserId(), user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepositoryMap.remove(userId);
    }
}
