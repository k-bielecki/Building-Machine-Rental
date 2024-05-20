package com.myshop.machine.infrastructure.database;

import com.myshop.machine.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachinePostgreSQLRepositoryInterface extends JpaRepository<Machine, Long> {
}
