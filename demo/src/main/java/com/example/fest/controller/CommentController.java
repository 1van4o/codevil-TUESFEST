package com.example.fest.controller;

import com.example.fest.dto.CommentDto;
import com.example.fest.dto.JobApplicationDto;
import com.example.fest.entity.CommentDomain;
import com.example.fest.entity.JobApplicationDomain;
import com.example.fest.entity.UserDomain;
import com.example.fest.mapper.Mapper;
import com.example.fest.service.CommentService;
import com.example.fest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @PostMapping("/addComment")
    public Boolean AddComment(@RequestBody CommentDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        CommentDomain comment = Mapper.toComment(dto);
        comment.setUserId(user);
        comment.setJobApplicationId(dto.getJoApplicationId());//TODO

        return commentService.comment(comment);
    }


    @PostMapping("/showComments")// /comments/showComments
    public List<CommentDto> getAll(@RequestBody JobApplicationDomain jobApplicationDomain){
        return commentService.getComments();
    }
}