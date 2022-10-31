package com.sinfloo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sinfloo.demo.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
