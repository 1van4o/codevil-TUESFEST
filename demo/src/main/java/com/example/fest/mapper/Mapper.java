package com.example.fest.mapper;


//import com.example.fest.dto.CommentDto;
import com.example.fest.dto.CommentDto;
import com.example.fest.dto.JobApplicationDto;
import com.example.fest.dto.UserDto;
//import com.example.fest.entity.CommentDomain;
import com.example.fest.entity.CommentDomain;
import com.example.fest.entity.JobApplicationDomain;
import com.example.fest.entity.UserDomain;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserDto toDto(UserDomain entity){
        return new UserDto(entity.getUsername(), entity.getPassword(), entity.getType());
    }

    public static UserDomain toUser(UserDto dto, UserDomain password){
        return new UserDomain(null, dto.getUsername(), password.getPassword(), dto.getType());
    }

    public static JobApplicationDto toJobDto(JobApplicationDomain app){
        return new JobApplicationDto(null, app.getTitle(), app.getText());
    }
    public static JobApplicationDomain toJob(JobApplicationDto dto){
        return new JobApplicationDomain(dto.getTitle(), dto.getText());
    }

    public static CommentDomain toComment(CommentDto dto){
        return new CommentDomain(null, null, null, dto.getText());
    }

    public static CommentDto toCommentDto(CommentDomain entity){
        return new CommentDto(entity.getText(), entity.getJobApplicationId());
    }
}
