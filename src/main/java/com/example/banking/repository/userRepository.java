package com.example.banking.repository;

import com.example.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Long> {

    User findByUsername(String userName);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    User findByUserId(long userId);
}
