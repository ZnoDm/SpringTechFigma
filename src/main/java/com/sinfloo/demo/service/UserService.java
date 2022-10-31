package com.sinfloo.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sinfloo.demo.controller.dto.UserRegistroDTO;
import com.sinfloo.demo.model.User;

public interface UserService extends UserDetailsService{
    User save(UserRegistroDTO registrationDto);
    User findByUsername(String username);
    public List<User> listarUsuarios();
}
