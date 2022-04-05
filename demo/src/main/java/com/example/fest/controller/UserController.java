package com.example.fest.controller;

import com.example.fest.dto.UserDto;
import com.example.fest.entity.UserDomain;
import com.example.fest.loginSystem.JwtUserDetailsService;
import com.example.fest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    public Boolean register(@RequestBody UserDto dto){
        return userService.register(dto);
    }

    @GetMapping("hello")
    public String hello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());
        return user.getType().toString();
    }
}
