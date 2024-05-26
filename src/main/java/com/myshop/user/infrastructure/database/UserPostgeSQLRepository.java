package com.myshop.user.infrastructure.database;

import com.myshop.user.domain.User;
import com.myshop.user.domain.UserRepository;

import java.util.List;

public class UserPostgeSQLRepository implements UserRepository {

    private final UserPostgreSQLRepositoryInterface userPostgreSQLDatabase;

    public UserPostgeSQLRepository(UserPostgreSQLRepositoryInterface userPostgreSQLDatabase) {
        this.userPostgreSQLDatabase = userPostgreSQLDatabase;
    }

    @Override
    public List<User> getAllUsers() {
        return userPostgreSQLDatabase.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return userPostgreSQLDatabase.findById(userId).orElseThrow();
    }

    @Override
    public User addUser(User user) {
        return userPostgreSQLDatabase.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userPostgreSQLDatabase.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userPostgreSQLDatabase.deleteById(userId);
    }
}
