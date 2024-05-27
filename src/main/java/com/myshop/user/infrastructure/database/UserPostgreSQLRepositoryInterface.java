package com.myshop.user.infrastructure.database;

import com.myshop.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPostgreSQLRepositoryInterface extends JpaRepository<User, Long> {
}
