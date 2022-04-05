package com.example.fest.service;

import com.example.fest.dto.UserDto;
import com.example.fest.entity.UserDomain;

public interface UserService {
    Boolean save(UserDomain entity);
    Boolean register(UserDto dto);
    Boolean existsByUsername(String username);
    UserDomain findByUsername(String username);
}
