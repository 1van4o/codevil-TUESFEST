package com.example.fest.controller;

import com.example.fest.dto.JobApplicationDto;
import com.example.fest.entity.JobApplicationDomain;
import com.example.fest.entity.UserDomain;
import com.example.fest.enums.UserTypeEnum;
import com.example.fest.mapper.Mapper;
import com.example.fest.service.JobApplicationService;
import com.example.fest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/apps")
public class JobApplicationController {

    @Autowired
    JobApplicationService jobApplicationService;

    @Autowired
    UserService userService;

    @PostMapping("/createApp")
    public Boolean createApp(@RequestBody JobApplicationDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        JobApplicationDomain jobApplicationDomain = Mapper.toJob(dto);
        if(user.getType().equals(UserTypeEnum.EMPLOYER)){
            jobApplicationDomain.setEmployer(user);
        } else {
            throw new RuntimeException("You are volunteer");
        }

        return jobApplicationService.save(jobApplicationDomain);
    }

    @PostMapping("/updateApp")
    public Boolean updateApp(@RequestBody JobApplicationDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        JobApplicationDomain jobApplicationDomain = jobApplicationService.findById(dto.getId());
        if(user.getType().equals(UserTypeEnum.VOLUNTEER)){
            jobApplicationDomain.setVolunteer(user);
        } else {
            throw new RuntimeException("You are employer");
        }

        return jobApplicationService.update(jobApplicationDomain);
    }

    @GetMapping("/getAllApps")
    public List<JobApplicationDto> getAll(){
        return jobApplicationService.getAll();
    }
}
