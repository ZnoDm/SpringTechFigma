package com.sinfloo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinfloo.demo.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String nombre);

}
