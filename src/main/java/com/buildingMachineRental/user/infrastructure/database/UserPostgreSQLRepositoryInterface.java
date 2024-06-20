package com.buildingMachineRental.user.infrastructure.database;

import com.buildingMachineRental.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPostgreSQLRepositoryInterface extends JpaRepository<User, Long> {
}
