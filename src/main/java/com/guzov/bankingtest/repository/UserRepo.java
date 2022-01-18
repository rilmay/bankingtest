package com.guzov.bankingtest.repository;

import com.guzov.bankingtest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
