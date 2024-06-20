package com.buildingMachineRental.user.infrastructure.database;

import com.buildingMachineRental.user.domain.User;
import com.buildingMachineRental.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
